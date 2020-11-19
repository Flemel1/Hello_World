package com.example.programandroid_1.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.programandroid_1.data.FireStoreItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FireStoreRepository {
    private static final String TAG = "FireStoreRepository";
    private MutableLiveData<ArrayList<FireStoreItem>> itemList;
    private final String KEY_NIM = "nim";
    private final String KEY_NAMA = "nama";
    private final String KEY_NO_HP = "nohp";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String collectionPath = "biodata";

    public LiveData<ArrayList<FireStoreItem>> getAllItem() {
        if (itemList == null) {
            itemList = new MutableLiveData<>();
            final ArrayList<FireStoreItem> lists = new ArrayList<>();
            db.collection(collectionPath)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    FireStoreItem item = new FireStoreItem();
                                    Map<String, Object> mapResult = document.getData();
                                    item.setDocumentID(document.getId());
                                    item.setNim(mapResult.get(KEY_NIM).toString());
                                    item.setNama(mapResult.get(KEY_NAMA).toString());
                                    item.setNoHp(mapResult.get(KEY_NO_HP).toString());
                                    lists.add(item);
                                }
                                itemList.setValue(lists);
                            }
                            else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        return itemList;
    }
}
