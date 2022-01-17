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

import com.ilhamferdx.projectacademicdataapp.adapter.MatkulAdapter;
import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Matkul;

import java.util.ArrayList;
import java.util.List;

public class MainMatkul extends AppCompatActivity {

    private RecyclerView recyclerViewMatkul;
    private Button btnTambahMatkul;

    private AppDatabase database;
    private MatkulAdapter matkulAdapter;
    private List<Matkul> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_matkul);
        recyclerViewMatkul = findViewById(R.id.recycler_viewmatkul);
        btnTambahMatkul = findViewById(R.id.btn_tambahmatkul);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.matkulDao().getALl());
        matkulAdapter = new MatkulAdapter(getApplicationContext(), list);
        matkulAdapter.setDialog(new MatkulAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit Mata Kuliah", "Hapus Mata Kuliah"};
                dialog = new AlertDialog.Builder(MainMatkul.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainMatkul.this,
                                        TambahMatkul.class);
                                intent.putExtra("matkulid", list.get(position).matkulid);
                                startActivity(intent);
                                break;
                            case 1:
                                Matkul matkul = list.get(position);
                                database.matkulDao().delete(matkul);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerViewMatkul.setLayoutManager(layoutManager);
        recyclerViewMatkul.setAdapter(matkulAdapter);
        btnTambahMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMatkul.this, TambahMatkul.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.matkulDao().getALl());
        matkulAdapter.notifyDataSetChanged();
    }
}