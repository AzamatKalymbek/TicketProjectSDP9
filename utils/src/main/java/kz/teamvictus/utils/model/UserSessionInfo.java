package kz.teamvictus.utils.model;

import java.io.Serializable;

public class UserSessionInfo implements Serializable {

    private String userId;
    private String username;
    private String authority;
    private String session;
    private String bonitaSessionId;
    private String bonitaSession;
    private String bonitaApiToken;
    private String headersJson;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getBonitaSessionId() {
        return bonitaSessionId;
    }

    public void setBonitaSessionId(String bonitaSessionId) {
        this.bonitaSessionId = bonitaSessionId;
    }

    public String getBonitaSession() {
        return bonitaSession;
    }

    public void setBonitaSession(String bonitaSession) {
        this.bonitaSession = bonitaSession;
    }

    public String getBonitaApiToken() {
        return bonitaApiToken;
    }

    public void setBonitaApiToken(String bonitaApiToken) {
        this.bonitaApiToken = bonitaApiToken;
    }

    public String getHeadersJson() {
        return headersJson;
    }

    public void setHeadersJson(String headersJson) {
        this.headersJson = headersJson;
    }
}
