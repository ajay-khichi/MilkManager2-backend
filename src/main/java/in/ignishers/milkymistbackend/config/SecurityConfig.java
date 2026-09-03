package in.ignishers.milkymistbackend.config;


import in.ignishers.milkymistbackend.services.SellerUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SellerUserDetailsService sellerUserDetailsService;

    // BCrypt for hashing/verifying sellers.password_hash. Use this same bean
    // wherever a seller sets/changes their password.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(sellerUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Pure API for a mobile client — no browser form/cookies involved,
                // so CSRF protection (which exists to defend cookie-based sessions) isn't needed.
                .csrf(csrf -> csrf.disable())

                // Stateless: every request re-authenticates via the Basic Auth header.
                // No HttpSession is created or read.
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // Open up health checks / actuator if you add them later
                        .requestMatchers("/actuator/health").permitAll()
                        // Everything else requires a logged-in seller.
                        // Add a customer, read-only rule here later, e.g.:
                        // .requestMatchers(HttpMethod.GET, "/api/bill/**").hasAnyRole("SELLER", "CUSTOMER")
                        .anyRequest().authenticated()
                )

                // HTTP Basic: client sends "Authorization: Basic base64(seller_id:password)"
                // on every request. Simple, stateless, fine over HTTPS for this use case.
                .httpBasic(basic -> {});

        return http.build();
    }
}

