package com.example.programandroid_1.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.programandroid_1.dao.UserDao;
import com.example.programandroid_1.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase database;
    public abstract UserDao userDao();

    public static synchronized UserDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return database;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
