package co.davsancamros.ricordati.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {

        return new ProviderManager(
                authenticationProvider()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authenticationProvider(
                        authenticationProvider()
                )

                .authorizeHttpRequests(auth -> auth


                        // Public Endpoints
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registration-otp").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/reset-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/reset-password-otp").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()


                        // User Endpoints - Admin
                        .requestMatchers(HttpMethod.GET, "/user/all").hasRole("ADMIN")


                        // User Endpoints - Regular User
                        .requestMatchers(HttpMethod.GET, "/user").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.PUT, "/user").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.DELETE, "/user").hasRole("REGULAR_USER")


                        // Task Category Endpoints - Regular User
                        .requestMatchers(HttpMethod.POST, "/task-category").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.GET, "/task-category/*").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.PUT, "/task-category/*").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.DELETE, "/task-category/*").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.GET, "/task-category").hasRole("REGULAR_USER")


                        // Task Status Endpoints - Regular User
                        .requestMatchers(HttpMethod.GET, "/task-status").hasRole("REGULAR_USER")


                        // Task Priority Level Endpoints - Regular User
                        .requestMatchers(HttpMethod.GET, "/task-priority-level").hasRole("REGULAR_USER")


                        // Task Reminder Time Endpoints - Regular User
                        .requestMatchers(HttpMethod.GET, "/task-reminder-time").hasRole("REGULAR_USER")


                        // Task Endpoints - Regular User
                        .requestMatchers(HttpMethod.POST, "/task").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.GET, "/task/*").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.PUT, "/task/*").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.DELETE, "/task/*").hasRole("REGULAR_USER")
                        .requestMatchers(HttpMethod.GET, "/task").hasRole("REGULAR_USER")


                        // Documentation Endpoints
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()


                        // All other Endpoints
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}