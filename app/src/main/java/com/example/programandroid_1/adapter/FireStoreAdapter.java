package com.example.programandroid_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programandroid_1.CrudActivity;
import com.example.programandroid_1.DetailActivity;
import com.example.programandroid_1.R;
import com.example.programandroid_1.data.FireStoreItem;

import java.util.ArrayList;

public class FireStoreAdapter extends RecyclerView.Adapter<FireStoreAdapter.ViewHolder> {

    private static final String TAG = "FireStoreAdapter";
    private Context context;
    private ArrayList<FireStoreItem> lists;
    private final String KEY_INTENT_DOCUMENT_ID = "id";
    private final String KEY_INTENT_NIM = "nim";
    private final String KEY_INTENT_NAMA = "nama";
    private final String KEY_INTENT_NO_HP = "nohp";

    public FireStoreAdapter(Context context, ArrayList<FireStoreItem> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.firestore_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        FireStoreItem item = lists.get(position);
        holder.nimMahasiswa.setText("NIM: " + item.getNim());
        holder.namaMahasiswa.setText("Nama: " + item.getNama());
        holder.noHpMahasiswa.setText("No HP: " + item.getNoHp());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                String documentID = lists.get(position).getDocumentID();
                String nim = lists.get(position).getNim();
                String nama = lists.get(position).getNama();
                String noHp = lists.get(position).getNoHp();
                intent.putExtra(KEY_INTENT_DOCUMENT_ID, documentID);
                intent.putExtra(KEY_INTENT_NIM, nim);
                intent.putExtra(KEY_INTENT_NAMA, nama);
                intent.putExtra(KEY_INTENT_NO_HP, noHp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nimMahasiswa;
        TextView namaMahasiswa;
        TextView noHpMahasiswa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nimMahasiswa = itemView.findViewById(R.id.txt_nim_mahasiswa);
            namaMahasiswa = itemView.findViewById(R.id.txt_nama_mahasiswa);
            noHpMahasiswa = itemView.findViewById(R.id.txt_noHp_mahasiswa);
        }
    }
}
