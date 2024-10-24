package dev.phani.roleanduribasedsecurity.secuirty;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    // create in memory user
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("charan").password("charan").roles("ADMIN").build();
        UserDetails  user1 = User.withUsername("baba").password("baba").roles("USER").build();
        return new InMemoryUserDetailsManager(Arrays.asList(user,user1));
    }

    @Bean
    public NoOpPasswordEncoder noOpPasswordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    // authenticate all requests based on the role
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests((authz) -> {
//            authz.anyRequest().fullyAuthenticated();
//        }).httpBasic(withDefaults());
//        return httpSecurity.build();
//    }

    // authenticate based on URI only, here allow all URI that start with /noAuth and don't allow that start with /auth
    // URI with /auth must go through login process whereas URI with /noAuth no need to go through login process
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> {
//            web.ignoring().requestMatchers(new AntPathRequestMatcher("/noAuth/**"));
//        };
//    }

    // authenticate the URI based on both URI and role based
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/auth/**", "/noAuth/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
        }).httpBasic(withDefaults());

        return httpSecurity.build();
    }

}
