package com.example.firstandroidapplication.service;

import com.example.firstandroidapplication.model.Message;
import com.example.firstandroidapplication.model.UserInfo;
import com.example.firstandroidapplication.model.UserSecurity;
import com.example.firstandroidapplication.model.UserAuthentification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @POST("/auth/login")
    Call<UserSecurity> authorization(@Body UserAuthentification userAuthentification);

    @GET("users/myprofile")
    Call<UserInfo> getMyProfile(@Header("Authorization") String token);

    @GET("users/contacts")
    Call<List<UserInfo>> getContacts(@Header("Authorization") String token);

    @GET("/users/chats")
    Call<List<UserInfo>> getChats(@Header("Authorization") String token);


    @GET("/users/chats/{username}")
    Call<List<Message>> getMessages(@Header("Authorization") String token, @Path("username") String username);

    @POST("/users/chats/new-message")
    Call<Object> sendMessage(@Header("Authorization") String token, @Body Message message);



}
