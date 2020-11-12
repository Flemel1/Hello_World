package com.example.programandroid_1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.programandroid_1.dao.UserDao;
import com.example.programandroid_1.database.UserDatabase;
import com.example.programandroid_1.entity.User;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private UserDatabase db;
    private LiveData<List<User>> allUser;

    public UserRepository(Application application) {
        db = UserDatabase.getDatabase(application);
        userDao = db.userDao();
        allUser = userDao.getAllUser();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getAllUser() {
        return allUser;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
