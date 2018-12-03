package kz.teamvictus.poll.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private String username;
    private Long id;
    private String token;
    private Collection<? extends GrantedAuthority> grantedAuthorityList;
    private CleanSecurityDBSession dbSession;

    public JwtUserDetails(String username, long id, String token, List<GrantedAuthority> grantedAuthorityList) {
        this.username = username;
        this.id = id;
        this.token = token;
        this.grantedAuthorityList = grantedAuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityList;
    }

    public CleanSecurityDBSession getDbSession() {
        return dbSession;
    }

    public void setDbSession(CleanSecurityDBSession dbSession) {
        this.dbSession = dbSession;
    }

    @Override
    public String toString() {
        return "JwtUserDetails{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", token='" + token + '\'' +
                ", grantedAuthorityList=" + grantedAuthorityList +
                '}';
    }
}
