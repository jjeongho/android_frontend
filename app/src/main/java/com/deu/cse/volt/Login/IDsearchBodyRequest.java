package com.deu.cse.volt.Login;

public class IDsearchBodyRequest {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    IDsearchBodyRequest(String email) {

        this.email = email;
    }
}
