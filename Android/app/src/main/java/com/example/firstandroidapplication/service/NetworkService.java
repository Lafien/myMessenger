package com.example.firstandroidapplication.service;

import com.example.firstandroidapplication.model.Message;
import com.example.firstandroidapplication.model.UserAuthentification;
import com.example.firstandroidapplication.model.UserInfo;
import com.example.firstandroidapplication.model.UserSecurity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Path;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "http://172.20.10.5:8080";
    private UserApi mRetrofit;


    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi.class);
    }


    public static NetworkService getInstance() {
        if(mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public Call<UserSecurity> authorizationUser(@Body UserAuthentification userAuthentification){
        return mRetrofit.authorization(userAuthentification);
    }

    public Call<UserInfo> myProfile(@Header("Authorization") String token){
        return mRetrofit.getMyProfile(token);
    }

    public  Call<List<UserInfo>> getContacts(@Header("Authorization") String token){
        return mRetrofit.getContacts(token);
    }

    public Call<List<UserInfo>> getChats(@Header("Authorization") String token){
        return mRetrofit.getChats(token);
    }

    public Call<List<Message>> getMessages(@Header("Authorization") String token, @Path("username") String username){
        return mRetrofit.getMessages(token, username);
    }

    public Call<Object> sendMessage(@Header("Authorization") String token, @Body Message message){
        return mRetrofit.sendMessage(token, message);
    }

}
