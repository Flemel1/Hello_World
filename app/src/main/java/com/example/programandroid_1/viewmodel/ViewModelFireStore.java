package com.example.programandroid_1.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.programandroid_1.data.FireStoreItem;
import com.example.programandroid_1.repository.FireStoreRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ViewModelFireStore extends ViewModel {
    private static final String TAG = "ViewModelFireStore";
    private LiveData<ArrayList<FireStoreItem>> itemList;
    private FireStoreRepository repository;


    private static ViewModelFireStore myViewModel;

    public static synchronized ViewModelFireStore getInstance() {
        if (myViewModel == null) {
            myViewModel = new ViewModelFireStore();
            return myViewModel;
        }
        return myViewModel;
    }

    public LiveData<ArrayList<FireStoreItem>> getAllItem() {
        if (repository == null) {
            Log.d(TAG, "created");
            repository = new FireStoreRepository();
            itemList = repository.getAllItem();
            return itemList;
        }
        return itemList;
    }
}
