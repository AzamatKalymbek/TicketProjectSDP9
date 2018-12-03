package kz.teamvictus.poll.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.teamvictus.poll.security.model.JwtUser;
import org.springframework.stereotype.Component;

import static kz.teamvictus.utils.Constants.SECRET_KEY;

@Component
public class JwtGenerator {
    public String generate(JwtUser jwtUser) {
        System.out.println("CALLED :: JwtGenerator.generate()");
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUsername());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
