package com.deu.cse.volt.Util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class BearerAuthIntercepter implements Interceptor {
    private String bearerToken;

    public BearerAuthIntercepter(String bearerToken ) {
        this.bearerToken = bearerToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder().addHeader("Authorization","Bearer "+bearerToken).build();
        return chain.proceed(authenticatedRequest);
    }
}