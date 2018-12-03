package kz.teamvictus.poll.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kz.teamvictus.poll.security.model.JwtUser;
import org.springframework.stereotype.Component;

import static kz.teamvictus.utils.Constants.SECRET_KEY;

@Component
public class JwtValidator {

    public JwtUser validate(String token) {
        System.out.println("CALLED :: JwtValidator.validate()");
        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String)body.get("userId")));
            jwtUser.setRole((String)body.get("role"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return jwtUser;
    }
}
