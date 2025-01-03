package co.id.ogya.lokakarya.security.config;

import co.id.ogya.lokakarya.security.util.JwtValidationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain mySecurityConfig(HttpSecurity http) throws Exception {
        log.info("Configuring security filter chain");

        http
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .cors(cors -> {
                    cors.configurationSource(request -> {
                        CorsConfiguration cfg = new CorsConfiguration();
                        cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                        cfg.setAllowedMethods(Collections.singletonList("*"));
                        cfg.setAllowCredentials(true);
                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                        cfg.setExposedHeaders(List.of("Authorization"));
                        return cfg;
                    });
                })
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/auth/sign-in").permitAll()
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/**")
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling
                            .authenticationEntryPoint((request, response, authException) -> {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                                response.setContentType("application/json");
                                response.getWriter().write("{\"error\": \"Unauthorized\"}");
                            });
                })
                .addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class);

        log.info("Security filter chain configuration complete");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Configuring password encoder");
        return new BCryptPasswordEncoder();
    }
}
