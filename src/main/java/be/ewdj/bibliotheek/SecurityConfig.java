package be.ewdj.bibliotheek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("user")).roles("USER").and()
                .withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/").permitAll()
                .requestMatchers("/403**").permitAll()
                .requestMatchers("/catalogus").permitAll()
                .requestMatchers("/detail/*").permitAll()
                .requestMatchers("/favorieten").permitAll()
                .requestMatchers("/nieuwBoek").hasRole("ADMIN"))
                .formLogin(form -> form.defaultSuccessUrl("/catalogus", true))
                .exceptionHandling(handling -> handling.accessDeniedPage("/403"));

        return http.build();
    }
}