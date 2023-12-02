package backend.config;

import backend.dto.response.AdminResponseDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAuthProvider {
    public static final String FIRST_NAME_REQUEST = "firstName";
    public static final String LAST_NAME_REQUEST = "lastName";

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expire-length.ms}")
    private long validityPeriod;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(AdminResponseDto dto) {
        Date now = new Date();

        Date validity = new Date(now.getTime() + validityPeriod);
        return JWT.create()
                .withIssuer(dto.getLogin())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim(FIRST_NAME_REQUEST, dto.getFirstName())
                .withClaim(LAST_NAME_REQUEST, dto.getLastName())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedToken = verifier.verify(token);

        AdminResponseDto admin = new AdminResponseDto()
                .setLogin(decodedToken.getIssuer())
                .setFirstName(decodedToken.getClaim(FIRST_NAME_REQUEST).asString())
                .setLastName(decodedToken.getClaim(LAST_NAME_REQUEST).asString());
        return new UsernamePasswordAuthenticationToken(admin, null, Collections.emptyList());
    }
}
