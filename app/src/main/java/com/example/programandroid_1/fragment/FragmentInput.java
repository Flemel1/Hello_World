package com.example.programandroid_1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.programandroid_1.R;
import com.example.programandroid_1.databinding.FragmentInputBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FragmentInput extends Fragment implements View.OnClickListener {

    private final String KEY_NIM = "nim";
    private final String KEY_NAMA = "nama";
    private final String KEY_NO_HP = "nohp";

    private FragmentInputBinding fragmentInputBinding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FirebaseFirestore db;
    private final String TAG = "FragmentInput";
    private final String collectionPath = "biodata";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentInputBinding = FragmentInputBinding.inflate(inflater, container, false);
        return fragmentInputBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fragmentInputBinding.btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        submitData();
    }

    private void submitData() {
        Map<String, String> biodata = new HashMap<>();
        String nim = fragmentInputBinding.edNim.getText().toString().trim();
        String nama = fragmentInputBinding.edNama.getText().toString().trim();
        String noHp = fragmentInputBinding.edNoHp.getText().toString().trim();
        if(!nim.isEmpty() && !nama.isEmpty() && !noHp.isEmpty()) {
            biodata.put(KEY_NIM, nim);
            biodata.put(KEY_NAMA, nama);
            biodata.put(KEY_NO_HP, noHp);
            db.collection(collectionPath)
                    .add(biodata)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
            fragmentInputBinding.edNim.setText("");
            fragmentInputBinding.edNama.setText("");
            fragmentInputBinding.edNoHp.setText("");
            fragmentTransaction.remove(this);
            fragmentTransaction.replace(R.id.frame_layout, new FragmentRoot());
            fragmentTransaction.commit();
        }
    }
}