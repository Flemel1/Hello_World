package com.example.programandroid_1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.programandroid_1.R;
import com.example.programandroid_1.adapter.FireStoreAdapter;
import com.example.programandroid_1.data.FireStoreItem;
import com.example.programandroid_1.databinding.FragmentListDataBinding;
import com.example.programandroid_1.viewmodel.UserFactory;
import com.example.programandroid_1.viewmodel.ViewModelFireStore;

import java.util.ArrayList;

public class FragmentListData extends Fragment {

    private FragmentListDataBinding fragmentListDataBinding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FireStoreAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentListDataBinding = FragmentListDataBinding.inflate(inflater,
                container,
                false);
        return fragmentListDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        layoutManager = new LinearLayoutManager(getContext());
        fragmentListDataBinding.rcFirestoreItem.setLayoutManager(layoutManager);

        ViewModelFireStore viewModelFireStore = ViewModelProviders.of(getActivity())
                .get(ViewModelFireStore.class);
        viewModelFireStore.getAllItem().observe(getViewLifecycleOwner(), new Observer<ArrayList<FireStoreItem>>() {
            @Override
            public void onChanged(ArrayList<FireStoreItem> fireStoreItems) {
                adapter = new FireStoreAdapter(getContext(), fireStoreItems);
                fragmentListDataBinding.rcFirestoreItem.setAdapter(adapter);
            }
        });
    }
}