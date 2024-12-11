package co.id.ogya.lokakarya.security.config;

import java.util.Arrays;
import java.util.Collections;

import co.id.ogya.lokakarya.security.util.JwtGeneratorFilter;
import co.id.ogya.lokakarya.security.util.JwtValidationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

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
                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                        return cfg;
                    });
                })
                .authorizeHttpRequests(auth -> {
                    auth
                            // Routes accessible to everyone
                            .requestMatchers("/auth/sign-in", "/v3/api-docs/**", "/swagger-ui/**").permitAll()

                            // Public GET routes
                            .requestMatchers(HttpMethod.GET, "/appuser/get/{id}", "/appuser/get/common/all", "/empachievementskill/**", "/empattitudeskill/**", "/empsuggestion/**", "/division/all", "/attitudeskill/**", "/achievement/**", "/appuser/div/**").hasAnyRole("HR", "USER", "SVP", "MGR")

                            // Routes accessible by HR, USER, SVP, MGR (GET only)
                            .requestMatchers("/assessmentsummary/**", "/auth/changepassword").hasAnyRole("HR", "USER", "SVP", "MGR")

                            // Allow both USER and HR to GET /groupattitudeskill/** and /groupachievement/**
                            .requestMatchers(HttpMethod.GET, "/groupattitudeskill/**", "/groupachievement/**", "/technicalskill/**", "/devplan/**").hasAnyRole("USER", "HR")

                            // Routes accessible by USER (any method)
                            .requestMatchers("/empattitudeskill/**", "/emptechnicalskill/**", "/empdevplan/**",
                                    "/attitudeskill/**",
                                    "/empsuggestion/**", "/empachievementskill/**").hasAnyRole("USER")

                            // Routes accessible by HR (broader coverage)
                            .requestMatchers("/appuser/**", "/division/**", "/approlemenu/**",
                                    "/groupattitudeskill/**", "/attitudeskill/**", "/technicalskill/**", "/devplan/**", "/groupachievement/**",
                                    "/achievement/**", "/empachievementskill/**", "/auth/resetpassword", "/assessmentsummary/**")
                            .hasAnyRole("HR")

                            // Default: all other requests require authentication
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
