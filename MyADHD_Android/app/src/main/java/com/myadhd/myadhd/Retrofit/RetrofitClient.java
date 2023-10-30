package com.myadhd.myadhd.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static Retrofit retrofit;
//    private static final String BASE_URL = "http://XXXXXXX-env.eba-cqjwgpej.us-east-1.elasticbeanstalk.com/";
    private static final String BASE_URL = "http://192.168.59.87:8080/";


    public static Retrofit getRetrofitInstance() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
