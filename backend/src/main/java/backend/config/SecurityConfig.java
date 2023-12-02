package backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static final String SWAGGER_PAGES_PATH = "/swagger-ui/**";
    public static final String SWAGGER_RESOURCES_PATH = "/swagger-resources/**";
    public static final String SWAGGER_DOCS_PATH = "/v3/api-docs/**";
    private static final String REGISTRATION_PATH = "/admin/register";
    private static final String LOGIN_PATH = "/admin/login";
    private final AdminAuthProvider adminAuthProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthFilter(adminAuthProvider),
                        BasicAuthenticationFilter.class)
                .sessionManagement(customer ->
                        customer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers(HttpMethod.POST,
                                        REGISTRATION_PATH, LOGIN_PATH)
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,
                                        SWAGGER_PAGES_PATH, SWAGGER_RESOURCES_PATH,
                                        SWAGGER_DOCS_PATH)
                                .permitAll()
                                .anyRequest().authenticated());
        return http.build();
    }
}






























