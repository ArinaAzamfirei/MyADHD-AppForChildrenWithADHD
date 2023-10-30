package com.myadhd.myadhd.DTOS;


public class Register {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public Register(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.username = username;
    }


}
