package com.example.firstandroidapplication.authorization;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ModelFactoryAuthorization extends ViewModelProvider.NewInstanceFactory {

    private UserAuthorization userAuthorization;

    public ModelFactoryAuthorization(UserAuthorization userAuthorization){
        super();
        this.userAuthorization = userAuthorization;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == AuthorisationViewModel.class) {
            return (T) new AuthorisationViewModel(userAuthorization);
        }
        return null;
    }

    public UserAuthorization getUserAuthorization() {
        return userAuthorization;
    }

    public void setUserAuthorization(UserAuthorization userAuthorization) {
        this.userAuthorization = userAuthorization;
    }
}
