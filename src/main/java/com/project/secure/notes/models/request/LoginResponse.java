package com.project.secure.notes.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// LoginResponse.java
@Setter
@Getter
public class LoginResponse {
    private String jwtToken;
    private String username;
    private List<String> roles;

    public LoginResponse(String username, List<String> roles, String jwtToken) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

}
