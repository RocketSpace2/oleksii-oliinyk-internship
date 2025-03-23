package api.gateway.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class GatewaySecurityConfig {
    private final String SECRET_KEY = "3hvmah0D8Oj1kn8lK07V7vMdtzKkeXPDju2JxfGx1uOhYJ2q9nBPmFAy8LBJoiQ9MyGQjBCmK/UHxUIGXMVRPw==";

    @Bean
    public GlobalFilter customAuthFilter() {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            HttpMethod method = exchange.getRequest().getMethod();

            if (path.startsWith("/seats") || path.startsWith("/flights")) {
                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    return unauthorized(exchange);
                }

                String token = authHeader.substring(7);
                Claims claims;
                try {
                    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
                    claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
                } catch (Exception e) {
                    return unauthorized(exchange);
                }

                String role = claims.get("role", String.class);

                if (path.startsWith("/seats")) {
                    if (path.contains("/confirm") && !isInRole(role, "WORKER")) {
                        return unauthorized(exchange);
                    } else if (path.contains("/cancel") && !isInRole(role, "USER", "WORKER", "ADMIN")) {
                        return unauthorized(exchange);
                    }

                    String userId = claims.getSubject();
                    ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                            .header("User-Id", userId)
                            .build();
                    ServerWebExchange modifiedExchange = exchange.mutate()
                            .request(modifiedRequest)
                            .build();
                    return chain.filter(modifiedExchange);
                } else if (path.startsWith("/flights")) {
                    if (!method.equals(HttpMethod.GET) && !isInRole(role, "WORKER")) {
                        return unauthorized(exchange);
                    }
                }
            }

            // For all other paths, continue without additional processing.
            return chain.filter(exchange);
        };
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(auth -> auth
                    .anyExchange().permitAll()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    private boolean isInRole(String userRole, String... allowedRoles) {
        for (String allowed : allowedRoles) {
            if (allowed.equalsIgnoreCase(userRole)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
