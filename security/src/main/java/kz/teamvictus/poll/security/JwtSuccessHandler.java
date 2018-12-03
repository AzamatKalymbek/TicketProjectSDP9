package kz.teamvictus.poll.security;

import kz.teamvictus.poll.security.model.JwtUserDetails;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LOGGER.debug("Successfully authenticated");
//        LOGGER.debug("AuthenticationSuccess session: " + httpServletRequest.getSession().getId());

//        getRedirectStrategy().sendRedirect(httpServletRequest, response, "/");
//        httpServletRequest.getSession().setMaxInactiveInterval(600); // optional, inactive session limit set to 10 minutes

//        super.clearAuthenticationAttributes(request);
    }
}
