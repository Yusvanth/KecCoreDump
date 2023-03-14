package com.keccoredump.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
	
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    	httpSecurity.cors();
        httpSecurity.csrf().disable();

        httpSecurity.authorizeHttpRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/save").permitAll()
        .antMatchers("/forget-password").permitAll()
        .antMatchers("/reset-password").permitAll()
        .antMatchers("/verify-otp").permitAll()
        .antMatchers("/generate-otp").permitAll()
        .antMatchers("/refresh-token").permitAll()
        .antMatchers("/admin").hasAnyAuthority("ADMIN")
        .antMatchers("/user").hasAnyAuthority("USER")
        //.antMatchers("/add-question").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        .logout().disable();
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();

    }
}
