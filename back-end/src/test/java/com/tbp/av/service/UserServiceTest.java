package com.tbp.av.service;


import com.tbp.av.model.User;
import com.tbp.av.model.factory.UserFactory;
import com.tbp.av.repository.UserRepository;
import com.tbp.av.support.StringSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(value = MockitoJUnitRunner.class)
public class UserServiceTest {

    public static final String SALT = "salt";
    public static final String PASS = "pass";
    public static final String ENCODED_PASS = "123";
    public static final String USERNAME = "username";
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    ShaPasswordEncoder shaPasswordEncoder;
    @Mock
    StringSupport stringSupport;
    @Mock
    UserFactory userFactory;

    User user;
    @Before
    public void before() {
        when(stringSupport.generate()).thenReturn(SALT);
        when(shaPasswordEncoder.encodePassword(PASS, SALT)).thenReturn(ENCODED_PASS);
        user = new User();
        when(userFactory.create(USERNAME, ENCODED_PASS, SALT)).thenReturn(user);
    }

    @Test
    public void testCreate() {
        userService.create(USERNAME, PASS);
        verify(userRepository).save(user);
    }

    @Test
    public void testIsLoginValidEmptyParams() {
        assertFalse(userService.isLoginValid(null, null));
        assertFalse(userService.isLoginValid("", null));
        assertFalse(userService.isLoginValid(null, ""));
        verifyZeroInteractions(userRepository, shaPasswordEncoder);
    }

    @Test
    public void testIsLoginValidNullUser() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(null);
        assertFalse(userService.isLoginValid(USERNAME, PASS));
        verifyZeroInteractions(shaPasswordEncoder);
    }

    @Test
    public void testIsLoginValidDiffPass() {
        User u = new User(USERNAME, PASS, SALT);
        when(userRepository.findByUsername(USERNAME)).thenReturn(u);
        assertFalse(userService.isLoginValid(USERNAME, PASS));
    }

    @Test
    public void testIsLoginValidSuccess() {
        User u = new User(USERNAME, ENCODED_PASS, SALT);
        when(userRepository.findByUsername(USERNAME)).thenReturn(u);
        assertTrue(userService.isLoginValid(USERNAME, PASS));
    }

    @Test
    public void testFindByUsername() {
        User u = new User(USERNAME, PASS, SALT);
        when(userRepository.findByUsername(USERNAME)).thenReturn(u);
        assertEquals(u, userService.findByUsername(USERNAME));
    }

}
