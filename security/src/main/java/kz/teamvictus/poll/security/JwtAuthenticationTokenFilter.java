package kz.teamvictus.poll.security;

import kz.teamvictus.poll.security.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.teamvictus.utils.Constants.TOKEN_PREFIX;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("CALLED :: JwtAuthenticationTokenFilter.attemptAuthentication()");

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.replaceAll(TOKEN_PREFIX, "");
        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("CALLED :: JwtAuthenticationTokenFilter.successfulAuthentication()");
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
