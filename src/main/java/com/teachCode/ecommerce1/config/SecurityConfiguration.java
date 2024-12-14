package com.teachCode.ecommerce1.config;

import com.teachCode.ecommerce1.services.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
//L'annotation @EnableWebSecurity active la securité web de spring et configure les aspects
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
private final JwtAuthenticationFilter jwtAuthenticationFilter;
private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
            //Une liste blanche des requetes {"/api/v1/auth/**"}
            .authorizeRequests()
            .requestMatchers("/api/v1/auth/**").permitAll() // Allow open access to auth endpoints
            .requestMatchers("/product/**").permitAll() // Temporarily permit all requests to "/product/**" for testing
            .anyRequest().authenticated() //toute autre requete hors ligne 30 et 31 doit étre authentifiée

            .and()
            //Gestion sans état
            //ce qui signifie que nous ne devrions pas stocker l'etat d'authentification
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Stateless session management
            //Dao AuthenticationProvider - qui est responsable de récupérer les information utils et d'rncoder /décoder les mots de passe
            //Ajout de JwtAuthenticationFilter avant UsernamePasswordAuthenticationFilter
            //car nous extrayons  le nom d'utilisateur et le mot de passe
            //PUIS LES METTONS à JOUR DANS LA SecurityContextHolder DANS jwtAuthenticationFilter
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter

    return http.build();

}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
