package kz.teamvictus.poll.security.service;

import kz.teamvictus.poll.core.model.User;
import kz.teamvictus.poll.core.service.IUserService;
import kz.teamvictus.poll.security.model.JwtUserDetails;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class SecurityService implements ISecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);
    private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IUserService userService;

    public HashMap<String, Object> getSessionInfo(String token) throws InternalException {

        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || auth.getPrincipal() == null || !auth.isAuthenticated()
                    || !(auth.getPrincipal() instanceof JwtUserDetails)) {
                sessionService.setSessionClosed(token);
                throw IE_HELPER.generate(ErrorCode.ErrorCodes.NOT_AUTHENTICATED, "Empty authentication");
            }

            JwtUserDetails jwtUserDetails = (JwtUserDetails) auth.getPrincipal();
            User user = userService.getUserByUsername(jwtUserDetails.getUsername());
            List<GrantedAuthority> authorityList = (List<GrantedAuthority>) jwtUserDetails.getGrantedAuthorityList();

            HashMap map = new HashMap();
            map.put("userId", user.getId());
            map.put("username", user.getUsername());
            map.put("authorityList", authorityList);

            return map;

        } catch (InternalException iex) {
            throw iex;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Ошибка при получении сессии", e);
        }
    }

    public String getSessionUsername() throws InternalException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || authentication.getPrincipal() == null || !authentication.isAuthenticated()
                    || !(authentication.getPrincipal() instanceof JwtUserDetails)) {
                throw IE_HELPER.generate(ErrorCode.ErrorCodes.NOT_AUTHENTICATED, "Пользователь не авторизован.");
            }

            JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
            LOGGER.debug("getSessionUsername username: " + userDetails.getUsername());

            return userDetails.getUsername();

        } catch (InternalException ie) {
            throw ie;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, " Ошибка при получении сессии", e);
        }
    }
}
