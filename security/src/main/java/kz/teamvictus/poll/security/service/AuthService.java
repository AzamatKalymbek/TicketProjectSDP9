package kz.teamvictus.poll.security.service;

import kz.teamvictus.poll.core.model.User;
import kz.teamvictus.poll.core.service.IRoleService;
import kz.teamvictus.poll.core.service.IUserService;
import kz.teamvictus.poll.security.JwtGenerator;
import kz.teamvictus.poll.security.model.CleanSecurityDBSession;
import kz.teamvictus.poll.security.model.JwtUser;
import kz.teamvictus.poll.security.model.JwtUserDetails;
import kz.teamvictus.utils.AuthUtil;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthService implements IAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

    @Autowired
    JwtGenerator jwtGenerator;
    @Autowired
    ISessionService sessionService;
    @Autowired
    ISecurityService securityService;
    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;


    public String login(String username, String password, HttpServletResponse response) throws InternalException {

        try {

            if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {

                User user = userService.getUserByUsername(username);

                if (user != null) {

                    if (user.getIsDisabled()) {
                        throw new BadCredentialsException("Пользователь отключен.");
                    }

                    AuthUtil.checkPasswordHash(password, user.getPassword());

                    JwtUser jwtUser = new JwtUser();
                    jwtUser.setId(user.getId());
                    jwtUser.setUsername(user.getUsername());
                    jwtUser.setRole(roleService.getRoleById(Long.valueOf(user.getRoleId())).getName());
                    String token = jwtGenerator.generate(jwtUser);

                    JwtUserDetails jwtUserDetails = new JwtUserDetails(
                            jwtUser.getUsername(),
                            jwtUser.getId(),
                            token,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole())
                    );

                    Authentication auth = new UsernamePasswordAuthenticationToken(jwtUserDetails, password, jwtUserDetails.getAuthorities());
                    jwtUserDetails.setDbSession(saveSessionDb(auth));

                    LOGGER.debug("Successfully authenticated");

                    return token;
                } else {
                    throw IE_HELPER.generate(ErrorCode.ErrorCodes.USER_NOT_FOUND, "Пользователь не найден");
                }

            } else {
                throw IE_HELPER.generate(ErrorCode.ErrorCodes.EMPTY_CREDENTIALS, "Не заполнены обязательные поля для авторизации пользователя");
            }

        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.NOT_AUTHENTICATED, "Пользовател не авторизован");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.NOT_AUTHENTICATED, "Пользовател не авторизован");
        }
    }

    @Override
    public String logout(String token) throws InternalException {
        try {
            sessionService.setSessionClosed(token);
            return "Successfully logged out";
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Ошибка при выходе из системы");
        }
    }

    @Override
    public HashMap<String, Object> session(String token) throws InternalException {
        try {
            return securityService.getSessionInfo(token);
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Ошибка при получении сессии");
        }
    }

    private CleanSecurityDBSession saveSessionDb(Authentication auth) {

        try {

            LOGGER.debug("saveSessionDb: " + auth.isAuthenticated());
            if (auth.isAuthenticated()) {

                SecurityContextHolder.getContext().setAuthentication(auth);
                JwtUserDetails jwtUserDetails = (JwtUserDetails) auth.getPrincipal();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(new Date());
                CleanSecurityDBSession currentSession = new CleanSecurityDBSession();

                String sessionToken = jwtUserDetails.getToken();
                LOGGER.debug(String.format("Login %s authenticated successfully! Session: %s",
                        jwtUserDetails.getUsername(), sessionToken));

                CleanSecurityDBSession processingSession;
                CleanSecurityDBSession oldSession = sessionService.getSessionBySessionToken(sessionToken);

                if (oldSession != null) {
                    LOGGER.debug("*** Updating old session info with id: " + oldSession.getSessionId() + " username: "
                            + oldSession.getUsername() + " to new session username: " + jwtUserDetails.getUsername());
                    oldSession.setUsername(jwtUserDetails.getUsername());
                    oldSession.setUpdateDate(currentTime);
                    processingSession = sessionService.updateSession(oldSession.getSessionId(), oldSession);
                } else {
                    LOGGER.debug("*** Creating new session data for username: " + jwtUserDetails.getUsername() + " token: " + sessionToken);
                    currentSession.setUsername(jwtUserDetails.getUsername());
                    currentSession.setSessionToken(sessionToken);
                    currentSession.setCreateDate(currentTime);
                    currentSession.setUpdateDate(currentTime);
                    currentSession.setStateId(sessionService.getSessionStateByName("opened").getStateId());
                    currentSession.setContext("Web portal login success!");
                    currentSession.setLocale("ru");
                    processingSession = sessionService.createSession(currentSession);
                }

                return processingSession;
            }

            throw new RuntimeException(auth.getName() + " authentication failed");

        } catch (Exception e) {
            LOGGER.error("Error while creating session entity: ", e);
            throw new RuntimeException(e);
        }
    }
}
