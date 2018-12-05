package kz.teamvictus.poll.core.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kz.teamvictus.poll.core.service.IUserTokenService;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static kz.teamvictus.utils.Constants.SECRET_KEY;

@Service
public class UserTokenService implements IUserTokenService {
   private static final Logger LOGGER = LoggerFactory.getLogger(UserTokenService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Override
   public Long getUserIdFromToken(String token) throws InternalException {
      try {
         Claims body = Jwts.parser()
                 .setSigningKey(SECRET_KEY)
                 .parseClaimsJws(token)
                 .getBody();
         return Long.parseLong((String)body.get("userId"));
      } catch (Exception e) {
         System.out.println(e);
      }
      return null;
   }
}
