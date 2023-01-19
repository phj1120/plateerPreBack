package org.zerock.b2.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.zerock.b2.filter.JWTLoginFilter;
import org.zerock.b2.filter.TokenCheckFilter;
import org.zerock.b2.handler.JWTLoginSuccessHandler;
import org.zerock.b2.security.JWTUtil;
import org.zerock.b2.service.CustomUserDetailsService;

import java.util.Arrays;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 어노테이션으로 시큐리티 기능 사용할 것인지
public class CustomSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        log.info("[CustomSecurityConfig.filterChain]");
        customUserDetailsService.setPasswordEncoder(passwordEncoder());

//        http.formLogin();

        // api 서버라 csrf 설정 필요 없음
        http.csrf().disable();

        // cors 설정
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        // jwt 를 이용할 것이므로 세션은 사용하지 않겠다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // AuthenticationManager 설정 - 반드시 필요
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);

        // JWT 로그인용 필터 등록
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter("/api/generate");
        jwtLoginFilter.setAuthenticationManager(authenticationManager); // AuthenticationManager 설정 - 반드시 필요

        jwtLoginFilter.setAuthenticationSuccessHandler(new JWTLoginSuccessHandler(jwtUtil)); // 성공 핸들러 등록

        http.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class);

        TokenCheckFilter tokenCheckFilter = new TokenCheckFilter(jwtUtil, customUserDetailsService);
        http.addFilterBefore(tokenCheckFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
