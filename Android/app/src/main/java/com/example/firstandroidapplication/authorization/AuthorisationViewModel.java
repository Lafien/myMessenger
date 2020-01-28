package com.example.firstandroidapplication.authorization;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firstandroidapplication.API.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorisationViewModel extends ViewModel {


    UserAuthorization userAuthorization;

    MutableLiveData<UserSecurity> data;

    public AuthorisationViewModel(UserAuthorization userAuthorization) {
        this.userAuthorization = userAuthorization;
    }


    public LiveData<UserSecurity> getData() {
            data = new MutableLiveData<>();
            loadData();
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
                            data.postValue(post);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserSecurity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

}
