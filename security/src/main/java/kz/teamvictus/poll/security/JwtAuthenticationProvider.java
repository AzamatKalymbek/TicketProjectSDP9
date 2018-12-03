package kz.teamvictus.poll.security;

import kz.teamvictus.poll.security.model.JwtAuthenticationToken;
import kz.teamvictus.poll.security.model.JwtUser;
import kz.teamvictus.poll.security.model.JwtUserDetails;
import kz.teamvictus.poll.security.service.ISecurityService;
import kz.teamvictus.utils.error.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    JwtValidator validator;
    @Autowired
    ISecurityService securityService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        System.out.println("CALLED :: JwtAuthenticationProvider.retrieveUser()");

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();
        JwtUser jwtUser = validator.validate(token);

        if (jwtUser == null) {
            throw new RuntimeException("JWT Token is not correct");
        }

        try {
            securityService.getSessionInfo(token);
        } catch (InternalException e) {
            e.printStackTrace();
            throw new RuntimeException("Session timeout");
        }

        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole());

        for (GrantedAuthority g: grantedAuthorityList) {
            System.out.println("////////////////////////"+g.getAuthority());
        }

        return new JwtUserDetails(jwtUser.getUsername(), jwtUser.getId(), token, grantedAuthorityList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("CALLED :: JwtAuthenticationProvider.supports()");
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}


