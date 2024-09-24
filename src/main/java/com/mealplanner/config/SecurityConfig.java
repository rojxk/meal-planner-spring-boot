package com.mealplanner.config;

import com.mealplanner.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import javax.sql.DataSource;
import java.util.function.Supplier;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        theUserDetailsManager
                .setUsersByUsernameQuery("SELECT username, password, enabled FROM userdata WHERE username=?");

        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("SELECT username, role FROM roles where username=?");

        return theUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/login-page", "/meals/**").permitAll()
                        .requestMatchers("/").hasRole("USER")
                        .anyRequest().authenticated()
        )
        .formLogin(form ->
                form
                        .loginPage("/login-page")
                        .loginProcessingUrl("/authenticate-user")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()
        )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

    private AuthorizationDecision hasUsername(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        String username = context.getVariables().get("username");
        return new AuthorizationDecision(
                username != null && username.equals(authentication.get().getName())
        );
    }

}
