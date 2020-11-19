package com.example.programandroid_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.programandroid_1.databinding.ActivityDetailBinding;
import com.example.programandroid_1.fragment.FragmentUpdate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private final String KEY_INTENT_DOCUMENT_ID = "id";
    private final String KEY_INTENT_NIM = "nim";
    private final String KEY_INTENT_NAMA = "nama";
    private final String KEY_INTENT_NO_HP = "nohp";

    private ActivityDetailBinding activityDetailBinding;
    private Intent myIntent;
    private String documentID;
    private String nim;
    private String nama;
    private String noHp;
    private FirebaseFirestore db;
    private final String collectionPath = "biodata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(activityDetailBinding.getRoot());
        myIntent = getIntent();
        db = FirebaseFirestore.getInstance();
        documentID = myIntent.getStringExtra(KEY_INTENT_DOCUMENT_ID);
        nim = myIntent.getStringExtra(KEY_INTENT_NIM);
        nama = myIntent.getStringExtra(KEY_INTENT_NAMA);
        noHp = myIntent.getStringExtra(KEY_INTENT_NO_HP);
        activityDetailBinding.detailNimMahasiswa.setText("NIM: " + nim);
        activityDetailBinding.detailNamaMahasiswa.setText("Nama: " + nama);
        activityDetailBinding.detailNoHpMahasiswa.setText("No HP: " + noHp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void deleteData(View view) {
        db.collection(collectionPath).document(documentID)
            .delete()
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "DocumentSnapshot successfully deleted!");
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error deleting document", e);
                }
            });
        finish();
    }

    public void updateData(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        activityDetailBinding.linearLayout.setVisibility(View.GONE);
        ft.replace(R.id.frame_detail, new FragmentUpdate().newInstance(documentID,
                nim,
                nama,
                noHp));
        ft.commit();

    }
}