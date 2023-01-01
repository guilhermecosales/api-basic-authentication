package br.com.github.apibasicauthentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "api/account/**")
                .permitAll()
                .anyRequest()
                .hasAnyRole("USER", "ADMIN")
                .and()
                .httpBasic(Customizer.withDefaults())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

}
