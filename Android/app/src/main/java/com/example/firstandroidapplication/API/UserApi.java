package com.example.firstandroidapplication.API;

import com.example.firstandroidapplication.chats.Message;
import com.example.firstandroidapplication.users.UserInfo;
import com.example.firstandroidapplication.authorization.UserSecurity;
import com.example.firstandroidapplication.authorization.UserAuthorization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @POST("/auth/login")
    Call<UserSecurity> authorization(@Body UserAuthorization userAuthorization);

    @GET("users/myprofile")
    Call<UserInfo> getMyProfile(@Header("Authorization") String token);

    @GET("users/contacts")
    Call<List<UserInfo>> getContacts(@Header("Authorization") String token);

    @GET("/users/chats")
    Call<List<UserInfo>> getChats(@Header("Authorization") String token);


    @GET("/users/chats/{username}")
    Call<List<Message>> getMessages(@Header("Authorization") String token, @Path("username") String username);

    @POST("/users/chats/messages")
    Call<Object> sendMessage(@Header("Authorization") String token, @Body Message message);



}
