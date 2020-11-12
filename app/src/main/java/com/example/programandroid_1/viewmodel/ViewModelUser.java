package com.example.programandroid_1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.programandroid_1.entity.User;
import com.example.programandroid_1.repository.UserRepository;

import java.util.List;

public class ViewModelUser extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<User>> allUser;

    public ViewModelUser(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUser = repository.getAllUser();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public LiveData<List<User>> getAllUser() {
        return allUser;
    }
}
