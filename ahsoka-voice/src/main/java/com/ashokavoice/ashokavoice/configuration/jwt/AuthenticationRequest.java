package com.ashokavoice.ashokavoice.configuration.jwt;

public class AuthenticationRequest {
    private String username;
    private String password;

    public AuthenticationRequest(){

    }
    public AuthenticationRequest(String username,String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
