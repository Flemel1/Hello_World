package com.example.programandroid_1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.programandroid_1.R;
import com.example.programandroid_1.databinding.FragmentUpdateBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUpdate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpdate extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String TAG = "FragmentUpdate";

    private String documentID;
    private String nim;
    private String nama;
    private String noHp;
    private FirebaseFirestore db;
    private final String collectionPath = "biodata";

    FragmentUpdateBinding fragmentUpdateBinding;

    public FragmentUpdate() {
        // Required empty public constructor
    }
    public static FragmentUpdate newInstance(String param1, String param2, String param3, String param4) {
        FragmentUpdate fragment = new FragmentUpdate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            documentID = getArguments().getString(ARG_PARAM1);
            nim = getArguments().getString(ARG_PARAM2);
            nama = getArguments().getString(ARG_PARAM3);
            noHp = getArguments().getString(ARG_PARAM4);
            db = FirebaseFirestore.getInstance();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentUpdateBinding = FragmentUpdateBinding.inflate(inflater, container, false);
        return fragmentUpdateBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fragmentUpdateBinding.edNimMahasiswa.setText(nim);
        fragmentUpdateBinding.edNamaMahasiswa.setText(nama);
        fragmentUpdateBinding.edNoHpMahasiswa.setText(noHp);
        fragmentUpdateBinding.btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        updateData();
    }

    private void updateData() {
        String nim = fragmentUpdateBinding.edNimMahasiswa.getText().toString().trim();
        String nama = fragmentUpdateBinding.edNamaMahasiswa.getText().toString().trim();
        String noHp = fragmentUpdateBinding.edNoHpMahasiswa.getText().toString().trim();
        DocumentReference washingtonRef = db.collection(collectionPath).document(documentID);
        washingtonRef.update("nim", nim,
                            "nama", nama,
                            "nohp", noHp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
        getActivity().finish();
    }
}