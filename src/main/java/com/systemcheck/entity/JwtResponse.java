package com.systemcheck.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String refreshtoken;
    private final String role;
    private final String userId;

    public JwtResponse(String jwttoken, String refreshtoken, String role, String userId) {
        this.jwttoken = jwttoken; this.refreshtoken = refreshtoken; this.role = role; this.userId = userId;
    }
    public String getToken() {
        return this.jwttoken;
    }
    public String getRole() {
        return this.role;
    }
    public String getUserId(){
        return this.userId;
    }
}
