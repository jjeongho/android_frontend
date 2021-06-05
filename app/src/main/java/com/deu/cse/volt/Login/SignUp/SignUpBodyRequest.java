package com.deu.cse.volt.Login.SignUp;

public class SignUpBodyRequest {
    final String username;
    final String password;
    final String email;

    SignUpBodyRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
