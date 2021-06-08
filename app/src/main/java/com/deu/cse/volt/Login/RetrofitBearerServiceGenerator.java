package com.deu.cse.volt.Login;

import com.deu.cse.volt.Util.BasicAuthInterceptor;
import com.deu.cse.volt.Util.BearerAuthIntercepter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBearerServiceGenerator {
    public static final String CLIENT_ID = "volt-android";
    public static final String CLIENT_PW = "volt";
    public static final String BASE_URL = "http://192.168.0.13:8443/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
            BearerAuthIntercepter interceptor =
                    new BearerAuthIntercepter(BearerTokenTemp.getInstance().getBearerToken());

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }


        return retrofit.create(serviceClass);
    }
}
