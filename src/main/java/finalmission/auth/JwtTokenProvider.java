package finalmission.auth;

import finalmission.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secretKey;

    public JwtTokenProvider(@Value("${jwt.key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String getToken(Member member) {
        return Jwts.builder()
            .claim(Claims.SUBJECT, member.getId().toString())
            .claim("email", member.getEmail())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }
}
