package com.mealplanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        theUserDetailsManager
                .setUsersByUsernameQuery("SELECT id, username, password, enabled FROM userdata WHERE id=?");

        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles where user_id=?");

        return theUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("ROLE_USER")
                        .anyRequest().authenticated()
        )
        .formLogin(form ->
                form
                        .loginPage("")
                        .loginProcessingUrl("").loginProcessingUrl("/authenticate-user")
                        .permitAll()
        )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

}
