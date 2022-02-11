package com.cozy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: allow more http methods here
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register/*").permitAll()
                .antMatchers(HttpMethod.POST, "/authenticate/*").permitAll()
                .antMatchers(HttpMethod.POST, "/dashboard/*").permitAll()
                .antMatchers(HttpMethod.POST, "/comment").permitAll()
                .antMatchers(HttpMethod.GET, "/comment/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/comment/*").permitAll()
                .antMatchers(HttpMethod.POST, "/post").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, 'true' as enabled FROM user WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user WHERE username = ?");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

