package com.example.firstandroidapplication.authorization;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firstandroidapplication.API.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationViewModel extends ViewModel {

    private UserAuthorization userAuthorization;

    private MutableLiveData<UserSecurity> data ;


    public MutableLiveData<UserSecurity> getData() {

        data = new MutableLiveData<>();

        return data;
    }

    public void setUserAuthorization(UserAuthorization userAuthorization) {
        this.userAuthorization = userAuthorization;
    }

    public void loadData(){
        ConfigRetrofit.getInstance()
                .authorizationUser(userAuthorization)
                .enqueue(new Callback<UserSecurity>() {
                    @Override
                    public void onResponse(Call<UserSecurity> call, Response<UserSecurity> response) {

                        if(response.isSuccessful()) {
                            UserSecurity post = response.body();
                            data.postValue(post);
                        }
                        else
                        {
                            data.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserSecurity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

}
