package com.vladzur.mysyncnotes.Model;

/**
 * Created by vladzur on 07-11-14.
 */
public class Account extends BaseModel {

    private int id;
    private String user;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
