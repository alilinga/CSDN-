package com.ll.config;

import com.ll.filter.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    MyFilter myFilter() throws Exception {
        MyFilter myFilter = new MyFilter();
        myFilter.setAuthenticationManager(authenticationManagerBean());
        return myFilter;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and().csrf().disable();
        http.addFilterAt(myFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
