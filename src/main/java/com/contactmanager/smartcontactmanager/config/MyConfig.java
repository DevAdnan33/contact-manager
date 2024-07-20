package com.contactmanager.smartcontactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfiguration {

    @Bean("bean1")
    public UserDetailsService customUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean("bean2")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("bean3")
    @org.springframework.context.annotation.Lazy
    public DaoAuthenticationProvider customAuthenticationProvider() {
        DaoAuthenticationProvider customAuthenticationProvider = new DaoAuthenticationProvider();
        customAuthenticationProvider.setUserDetailsService(this.customUserDetailsService());
        customAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());

        return customAuthenticationProvider;
    }

    // Configure
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider());
    }

    @SuppressWarnings({ "deprecation", "removal" })
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**")
                .hasRole("NORMAL").requestMatchers("/***").permitAll().and().formLogin().and().csrf().disable();
    }
}
