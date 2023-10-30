package com.myadhd.myadhd.DTOS;

public class Authenticate {

    private String email;
    private String password;

    public Authenticate(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
