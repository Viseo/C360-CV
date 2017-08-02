package com.viseo.c360.cv.models.dto;

public class Login {
    private String mail;
    private String password;
    private boolean known;

    public Login(String mail, String password, boolean known) {
        this.mail = mail;
        this.password = password;
        this.known = known;
    }

    public String getMail() {
        return this.mail;
    }
    public String getPassword() {
        return this.password;
    }

    public void setKnown(boolean known) {
        this.known=known;
    }
}


