package kz.teamvictus.poll.security.model;

public class JwtUser {

    private String username;
    private long id;
    private String role;

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
