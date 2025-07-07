package com.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Adding Configuration to the class makes it a java based configuration and we can define all the spring bean definitions within this class
@EnableMethodSecurity //To add method level security; Earlier "EnableGlobalMethodSecurity" annotation was used
public class SecurityConfig {//earlier before spring 3, this configuration was extending WebSecurityConfigurerAdapter. Now it is deprecated

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {;//this bean enables component based spring security style

        /*Enable only basic auth. If you run your app with this config:
        All requests to any endpoint (e.g. /students, /api, etc.) will require HTTP Basic Auth in the header of the request or in browser
        If no valid Authorization header is sent, the server will respond with a 401 Unauthorized.
         */
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    //Create 2 users and store them in the spring provided in-memory object
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails vaibhav = User.builder()
                .username("vaibhav")
                .password(passwordEncoder().encode("vaibhav"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(vaibhav,admin);
    }


}
