package com.example.tema4acdat.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiAdapter {

    private static ApiService.GitHubClient API_SERVICE;
    public static final String BASE_URL = "https://www.zaragoza.es/sede/";

    public static synchronized ApiService.GitHubClient getApiService() {
        if (API_SERVICE == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
                    .create();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
            API_SERVICE = retrofit.create(ApiService.GitHubClient.class);
        }
        return API_SERVICE;


    }
}
