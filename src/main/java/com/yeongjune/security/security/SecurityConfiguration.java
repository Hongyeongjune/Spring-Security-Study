package com.yeongjune.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
//                .withUser("hongyeongjune").password(passwordEncoder().encode("hongyeongjune")).roles("ADMIN")
//                .and()
//                .withUser("hongguri").password(passwordEncoder().encode("hongguri")).roles("USER")
//                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
