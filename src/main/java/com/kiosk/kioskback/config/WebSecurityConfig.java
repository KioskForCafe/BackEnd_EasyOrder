package com.kiosk.kioskback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kiosk.kioskback.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    protected SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors().and()
            .csrf().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().requestMatchers("/", "/auth/**", "/api/user/**", "/api/point/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/menu/**", "/api/category/**", "/file/**","/api/store/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/user/duplicate/**", "/file/**", "/api/order", "/sms/**").permitAll()
            .anyRequest().authenticated();

            httpSecurity
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    protected WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/swagger-ui/**","/swagger-ui.html", "/swagger-resources/**", "/swagger/**", "/api-docs/**", "/webjars/**","/css/**", "/images/**","/js/**","/v2/api-docs");
    }

}
