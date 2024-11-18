//package co.id.ogya.lokakarya.security.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import co.id.ogya.lokakarya.security.util.JwtGeneratorFilter;
//import co.id.ogya.lokakarya.security.util.JwtValidationFilter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.cors.CorsConfiguration;
//
//@Slf4j
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain mySecurityConfig(HttpSecurity http) throws Exception {
//        log.info("Configuring security filter chain");
//
//        http.sessionManagement(sessionManagement -> {
//                    log.debug("Setting session management policy to stateless");
//                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                }).cors(cors -> {
//                    cors.configurationSource(request -> {
//                        CorsConfiguration cfg = new CorsConfiguration();
//                        log.debug("Configuring CORS settings");
//                        cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
//                        cfg.setAllowedMethods(Collections.singletonList("*"));
//                        cfg.setAllowCredentials(true);
//                        cfg.setAllowedHeaders(Collections.singletonList("*"));
//                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                        log.debug("CORS configuration complete");
//                        return cfg;
//                    });
//                }).authorizeHttpRequests(auth -> {
//                    log.debug("Setting authorization rules");
//                    auth
////                            .requestMatchers(HttpMethod.POST, "/app/sign-up", "/app/sign-in").permitAll()
////                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
////                            .requestMatchers("/**").hasAnyRole("USER", "ADMIN")
//                            .requestMatchers("/**").permitAll()
//                            .anyRequest().permitAll();
////                            .anyRequest().authenticated();
//                }).csrf(csrf -> {
//                    log.debug("Configuring CSRF protection");
//                    csrf.ignoringRequestMatchers("/**")
//                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//                }).addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new JwtValidationFilter(), BasicAuthenticationFilter.class);
//
//        log.info("HTTP Basic and Form Login configurations applied");
//        http.httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
//
//        log.info("Security filter chain configuration complete");
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        log.info("Configuring password encoder");
//        return new BCryptPasswordEncoder();
//    }
//}
