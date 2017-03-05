package com.tbp.av.support;


import org.junit.Test;
import static org.junit.Assert.*;

public class StringSupportTest {

    @Test
    public void testGenerate() {
        StringSupport stringSupport = new StringSupport();
        assertNotNull(stringSupport.generate());
        assertTrue(stringSupport.generate().length() > 0);
    }

}
