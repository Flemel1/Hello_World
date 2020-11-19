package com.example.programandroid_1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.programandroid_1.R;
import com.example.programandroid_1.data.FireStoreItem;
import com.example.programandroid_1.databinding.FragmentRootBinding;
import com.example.programandroid_1.viewmodel.UserFactory;
import com.example.programandroid_1.viewmodel.ViewModelFireStore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FragmentRoot extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragmentRoot";
    private FragmentRootBinding fragmentRootBinding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRootBinding = FragmentRootBinding.inflate(inflater, container,false);
        return fragmentRootBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fragmentRootBinding.btnInputData.setOnClickListener(this);
        fragmentRootBinding.btnListData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_input_data : moveToInputDataFragment(); break;
            case R.id.btn_list_data : moveToListDataFragment();break;
        }
    }

    private void moveToListDataFragment() {
        fragmentTransaction.replace(R.id.frame_layout,new FragmentListData());
        fragmentTransaction.commit();
    }

    private void moveToInputDataFragment() {
        Log.i("msg", "yes");
        fragmentTransaction.replace(R.id.frame_layout, new FragmentInput());
        fragmentTransaction.commit();
    }
}