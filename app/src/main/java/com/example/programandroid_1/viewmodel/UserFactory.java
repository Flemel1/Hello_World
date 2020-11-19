package com.example.programandroid_1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;
    private ViewModel viewModel;

    public UserFactory(Application application) {
        this.application = application;
    }

    public UserFactory(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelUser.class)) {
            return (T) new ViewModelUser(application);
        }
        else if(modelClass.isAssignableFrom(ViewModelFireStore.class)) {
            ViewModelFireStore viewModelFireStore = (ViewModelFireStore) viewModel;
            return (T) viewModelFireStore;
        }
        return super.create(modelClass);
    }

}
