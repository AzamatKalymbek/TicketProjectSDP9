package kz.teamvictus.poll.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("CALLED :: JwtAuthenticationEntryPoint.commence()");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
