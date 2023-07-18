package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.club.security.filter.ApiCheckFillter;
import org.zerock.club.security.filter.ApiLoginFillter;
import org.zerock.club.security.handler.ApiLoginFailHandler;
import org.zerock.club.security.handler.ClubLoginSuccessHandler;
import org.zerock.club.util.JWTUtil;

import javax.servlet.Filter;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        /*
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");
         */
        http.formLogin();
        http.csrf().disable();
        http.logout();
        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService());
        http.addFilterBefore(apiCheckFillter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFillter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ClubLoginSuccessHandler successHandler(){
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$MrlpFgDAmwHMRH/H/Rsgrej.zN4fsblUa/hluJEdvFlnv3VHjE9PK")
                .roles("USER");
    }
*/
    @Bean
    public ApiLoginFillter apiLoginFillter() throws Exception {
        ApiLoginFillter apiLoginFillter = new ApiLoginFillter("/api/login", jwtUtil());
        apiLoginFillter.setAuthenticationManager(authenticationManager());

        apiLoginFillter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFillter;
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }


    @Bean
    public ApiCheckFillter apiCheckFillter() {
        return new ApiCheckFillter("/notes/**/*", jwtUtil());
    }
}
