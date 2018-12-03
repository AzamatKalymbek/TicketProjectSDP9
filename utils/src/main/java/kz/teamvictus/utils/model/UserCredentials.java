package kz.teamvictus.utils.model;

import java.io.Serializable;

public class UserCredentials implements Serializable {

    private String iin;
    private String password;

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
