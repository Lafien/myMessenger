package com.example.firstandroidapplication.API;

import com.example.firstandroidapplication.chats.Message;
import com.example.firstandroidapplication.authorization.UserAuthorization;
import com.example.firstandroidapplication.users.UserInfo;
import com.example.firstandroidapplication.authorization.UserSecurity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Path;

public class ConfigRetrofit {

    private static ConfigRetrofit mInstance;
    private static final String BASE_URL = "http://172.20.10.5:8080";
    private UserApi mRetrofit;


    private ConfigRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi.class);
    }


    public static ConfigRetrofit getInstance() {
        if(mInstance == null){
            mInstance = new ConfigRetrofit();
        }
        return mInstance;
    }

    public Call<UserSecurity> authorizationUser(@Body UserAuthorization userAuthorization){
        return mRetrofit.authorization(userAuthorization);
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
