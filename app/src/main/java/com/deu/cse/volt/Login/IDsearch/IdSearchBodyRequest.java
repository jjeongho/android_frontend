package com.deu.cse.volt.Login.IDsearch;

public class IdSearchBodyRequest {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    IdSearchBodyRequest(String email) {

        this.email = email;
    }
}
