package com.example.firstandroidapplication.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "http://172.20.10.5:8080";
    private Retrofit mRetrofit;


    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static NetworkService getInstance() {
        if(mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public UserApi authorizationUser(){
        return mRetrofit.create(UserApi.class);
    }

    public UserApi MyProfile(){
        return mRetrofit.create(UserApi.class);
    }

    public UserApi getContacts(){
        return mRetrofit.create(UserApi.class);
    }

    public UserApi getChats(){
        return mRetrofit.create(UserApi.class);
    }

    public UserApi getMessages(){
        return mRetrofit.create(UserApi.class);
    }

    public UserApi sendMessage(){
        return mRetrofit.create(UserApi.class);
    }

}
