package com.deu.cse.volt.Util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OAuth2Interceptor implements Interceptor {

    private String accessToken;

    public OAuth2Interceptor(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder().addHeader("Authorization",accessToken).build();
        return chain.proceed(authenticatedRequest);
    }
}
