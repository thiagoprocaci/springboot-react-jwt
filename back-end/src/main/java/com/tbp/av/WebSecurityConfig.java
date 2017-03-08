package com.tbp.av;

import com.tbp.av.security.*;
import com.tbp.av.security.filter.JwtAuthenticationTokenFilter;
import com.tbp.av.security.handler.AjaxAuthenticationFailureHandler;
import com.tbp.av.security.handler.AjaxAuthenticationSuccessHandler;
import com.tbp.av.security.handler.AjaxLogoutSuccessHandler;
import com.tbp.av.security.handler.Http401UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    @Autowired
    AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    @Autowired
    AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
    @Autowired
    Http401UnauthorizedEntryPoint authenticationEntryPoint;
    @Autowired
    AuthProviderService authProvider;
    @Autowired
    SecurityProperties security;
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permited = new String[security.getIgnored().size()];
        security.getIgnored().toArray(permited);

        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/user").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/favicon.ico").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/api/authentication")
                    .successHandler(ajaxAuthenticationSuccessHandler)
                    .failureHandler(ajaxAuthenticationFailureHandler)
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout()
                    .logoutUrl("/api/logout")
                    .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

    @Bean
    public ShaPasswordEncoder sha() {
        ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
        return shaPasswordEncoder;
    }
}
