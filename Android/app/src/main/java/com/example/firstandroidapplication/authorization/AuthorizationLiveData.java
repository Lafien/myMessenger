package com.example.firstandroidapplication.authorization;

import android.app.FragmentTransaction;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.FragmentMainPage;
import com.example.firstandroidapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationLiveData {

    UserAuthorization userAuthorization;
    String token;
    String authUser;

    MutableLiveData<UserSecurity> data;

    public LiveData<UserSecurity> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }


    private void loadData(){
        ConfigRetrofit.getInstance()
                .authorizationUser(userAuthorization)
                .enqueue(new Callback<UserSecurity>() {
                    @Override
                    public void onResponse(Call<UserSecurity> call, Response<UserSecurity> response) {

                        if(response.isSuccessful()) {
                            UserSecurity post = response.body();

                            token = "Bearer_" + post.getToken();
                            authUser = post.getUsername();
                            data.postValue(post);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserSecurity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    /*private void loadData() {
        dataRepository.loadData(new Callback<String>() {
            @Override
            public void onLoad(String s) {
                data.postValue(s);
            }
        });
    }*/

}
