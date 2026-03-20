////package com.mora.backendangkorliving.config;
////
////import com.mora.backendangkorliving.security.JwtFilter;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.HttpMethod;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@EnableWebSecurity
////@Configuration
////public class SecurityConfig {
////
////    @Autowired
////    private JwtFilter jwtFilter;
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                // Disable CSRF for stateless JWT
////                .csrf(csrf -> csrf.disable())
////                // Authorization rules
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/auth/**").permitAll()
////
////                        // Admin endpoints
////                        .requestMatchers("/api/users/**").hasRole("ADMIN")
////
////                        // Tenant/User endpoints (profile)
////                        .requestMatchers("/api/users/profile/**").hasAnyRole("TENANT", "USER")
////                        // Admin can create/update/delete floors
////
////                        // Tenant and Admin can view floors
////                        .requestMatchers(
////                                HttpMethod.GET,
////                                "/api/floors/**"
////                        ).permitAll()
////                        .requestMatchers( "/api/floors/**").hasRole("ADMIN")
////                        .requestMatchers(
////                                HttpMethod.GET,
////                                "/api/rooms/**"
////                        ).permitAll()
////                        .requestMatchers( "/api/rooms/**").hasRole("ADMIN")
////                        .requestMatchers(
////                                HttpMethod.GET,
////                                "/api/rentals/**"
////                        ).permitAll()
////                        .requestMatchers( "/api/rentals/**").hasRole("ADMIN")
////                        .requestMatchers(
////                                HttpMethod.GET,
////                                "/api/payments/**"
////                        ).permitAll()
////                        .requestMatchers( "/api/payments/**").hasRole("ADMIN")
////                        // Register/Login public
////                        .anyRequest().authenticated()              // Other endpoints require JWT
////                )
////                // Stateless session
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////        // Add JWT filter before UsernamePasswordAuthenticationFilter
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder(); // Secure password hashing
////    }
////}
//package com.mora.backendangkorliving.config;
//
//import com.mora.backendangkorliving.security.JwtFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Stateless JWT → disable CSRF
//                .csrf(csrf -> csrf.disable())
//
//                // Authorization rules
//                .authorizeHttpRequests(auth -> auth
//                        // Public endpoints
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/floors/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/rooms/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/rentals/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/payments/**").permitAll()
//
//                        // Admin-only endpoints
//                        .requestMatchers("/api/users/**").hasRole("ADMIN")
//                        .requestMatchers("/api/floors/**").hasRole("ADMIN")
//                        .requestMatchers("/api/rooms/**").hasRole("ADMIN")
//                        .requestMatchers("/api/rentals/**").hasRole("ADMIN")
//                        .requestMatchers("/api/payments/**").hasRole("ADMIN")
//
//                        // Tenant/User profile
//                        .requestMatchers("/api/users/profile/**").hasAnyRole("TENANT", "USER")
//
//                        // Everything else requires authentication
//                        .anyRequest().authenticated()
//                )
//
//                // Stateless session management
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        // Add JWT filter before UsernamePasswordAuthenticationFilter
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Secure password hashing
//    }
//}
package com.mora.backendangkorliving.config;

import com.mora.backendangkorliving.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // ✅ Enable CORS so Spring Security respects CorsConfig
//                .cors()
//                .and()
//                // Stateless JWT → disable CSRF
//                .csrf(csrf -> csrf.disable())
//
//                // Authorization rules
//                .authorizeHttpRequests(auth -> auth
//                        // Public endpoints
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/floors/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/rooms/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/rentals/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/payments/**").permitAll()
//
//                        // Admin-only endpoints
//                        .requestMatchers("/api/users/**").hasRole("ADMIN")
//                        .requestMatchers("/api/floors/**").hasRole("ADMIN")
//                        .requestMatchers("/api/rooms/**").hasRole("ADMIN")
//                        .requestMatchers("/api/rentals/**").hasRole("ADMIN")
//                        .requestMatchers("/api/payments/**").hasRole("ADMIN")
//
//                        // Tenant/User profile
//                        .requestMatchers("/api/users/profile/**").hasAnyRole("TENANT", "USER")
//
//                        // Everything else requires authentication
//                        .anyRequest().authenticated()
//                )
//
//                // Stateless session management
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        // Add JWT filter before UsernamePasswordAuthenticationFilter
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable()) // or cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable()) // ✅ disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/floors/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/rooms/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/rentals/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/payments/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/users/profile/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/users/profile/**").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/floors/**").hasRole("ADMIN")
                        .requestMatchers("/api/rooms/**").hasRole("ADMIN")
                        .requestMatchers("/api/rentals/**").hasRole("ADMIN")
                        .requestMatchers("/api/payments/**").hasRole("ADMIN")
                                //.requestMatchers("/api/users/**/reset-password/**").hasRole("ADMIN")


//                        .requestMatchers("/api/users/profile/**").hasAnyRole("TENANT", "USER")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ✅ new style
                );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Secure password hashing
    }
}
