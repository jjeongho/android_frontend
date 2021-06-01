package com.deu.cse.volt.Login;

import java.io.Serializable;

public class BearerTokenTemp {
    private static BearerTokenTemp b_token = new BearerTokenTemp();
    private String token;
    private BearerTokenTemp(){

    }

    public static BearerTokenTemp getInstance(){
        return b_token;
    }

    public void setBearerToken(String token){
        this.token = token;
    }

    public String getBearerToken(){
        return token;
    }

}
