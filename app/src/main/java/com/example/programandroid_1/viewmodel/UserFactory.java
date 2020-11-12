package com.example.programandroid_1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;

    public UserFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelUser.class)) {
            return (T) new ViewModelUser(application);
        }
        return super.create(modelClass);
    }

}
