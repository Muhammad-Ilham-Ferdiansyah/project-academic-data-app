package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ilhamferdx.projectacademicdataapp.adapter.DosenAdapter;
import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Dosen;

import java.util.ArrayList;
import java.util.List;

public class MainDosen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnTambah;

    private AppDatabase database;
    private DosenAdapter dosenAdapter;
    private List<Dosen> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Data Dosen");
        setContentView(R.layout.activity_main_dosen);
        recyclerView = findViewById(R.id.recycler_view);
        btnTambah = findViewById(R.id.btn_tambah);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.dosenDao().getAll());
        dosenAdapter = new DosenAdapter(getApplicationContext(), list);
        dosenAdapter.setDialog(new DosenAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit Dosen", "Hapus Dosen"};
                dialog = new AlertDialog.Builder(MainDosen.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainDosen.this,
                                        TambahDosen.class);
                                intent.putExtra("dsnid", list.get(position).dsnid);
                                startActivity(intent);
                                break;
                            case 1:
                                Dosen  dosen = list.get(position);
                                database.dosenDao().delete(dosen);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dosenAdapter);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDosen.this, TambahDosen.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.dosenDao().getAll());
        dosenAdapter.notifyDataSetChanged();
    }
}