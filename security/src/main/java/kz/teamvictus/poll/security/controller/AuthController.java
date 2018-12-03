package kz.teamvictus.poll.security.controller;

import kz.teamvictus.poll.security.service.IAuthService;
import kz.teamvictus.utils.CommonService;
import kz.teamvictus.utils.error.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.teamvictus.utils.Constants.TOKEN_PREFIX;

@RestController
@RequestMapping("/auth")
public class AuthController extends CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response,
                                   @RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password) {
        try {
            LOGGER.debug("CALLED :: AuthController.login");
            return builder(success(authService.login(username, password, response)));
        } catch (InternalException e) {
            LOGGER.error(e.getMessage(), e);
            return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam(value = "token") String token) {
        try {
            LOGGER.debug("CALLED :: AuthController.logout");
            return builder(success(authService.logout(token)));
        } catch (InternalException e) {
            LOGGER.error(e.getMessage(), e);
            return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
        }
    }

    @GetMapping("/session")
    public ResponseEntity<?> session(HttpServletRequest req) {
        try {
            String userToken = req.getHeader("Authorization").replace(TOKEN_PREFIX,"");
            return builder(success(authService.session(userToken)));
        } catch (InternalException e) {
            LOGGER.error(e.getMessage(), e);
            return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
        }
    }

}
