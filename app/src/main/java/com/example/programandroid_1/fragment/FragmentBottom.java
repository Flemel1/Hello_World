package com.example.programandroid_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programandroid_1.R;
import com.example.programandroid_1.adapter.RecyclerViewAdapter;
import com.example.programandroid_1.data.RecordItem;

import java.util.ArrayList;


public class FragmentBottom extends Fragment {
    private ArrayList<RecordItem> recordItems = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    private int[] imageId = new int[] {R.drawable.ic_launcher_background,
                                        R.drawable.ic_launcher_foreground};

    private String[] titles = new String[] {"Hello",
                                            "World"};

    private String[] descriptions = new String[] {"This is description for Hello film",
                                                    "This is description for World film"};

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.film_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter();
    }

    private void setAdapter() {
        for (int i = 0; i < 2; i++) {
            RecordItem item = new RecordItem();
            item.setIdImage(imageId[i]);
            item.setTitle(titles[i]);
            item.setDescription(descriptions[i]);
            recordItems.add(item);
        }
        adapter = new RecyclerViewAdapter(getContext(), recordItems);
        recyclerView.setAdapter(adapter);
    }
}