package com.chengp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by pc on 3/4/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index"
                            ,"/account/register"
                            ,"/account/register/available"
                            ,"/css/**", "/js/**", "/video/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.DELETE,"/admin/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .defaultSuccessUrl("/account")
                .loginPage("/login")
                .permitAll()
            .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/index");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("user").password("123456").roles("ADMIN");
        auth.userDetailsService(userDetailsService);
    }
}
