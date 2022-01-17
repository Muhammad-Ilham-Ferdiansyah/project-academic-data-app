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

import com.ilhamferdx.projectacademicdataapp.adapter.MahasiswaAdapter;
import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MainMahasiswa extends AppCompatActivity {

    private RecyclerView recyclerViewMhs;
    private Button btnTambahMhs;

    private AppDatabase database;
    private MahasiswaAdapter mahasiswaAdapter;
    private List<Mahasiswa> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Data Mahasiswa");
        setContentView(R.layout.activity_main_mahasiswa);
        recyclerViewMhs     = findViewById(R.id.recycler_viewmhs);
        btnTambahMhs        = findViewById(R.id.btn_tambahmhs);
        database            = AppDatabase.getInstance(getApplicationContext());

        list.clear();

        list.addAll(database.mahasiswaDao().getALl());

        mahasiswaAdapter = new MahasiswaAdapter(getApplicationContext(), list);
        mahasiswaAdapter.setDialog(new MahasiswaAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit Mahasiswa", "Hapus Mahasiswa"};
                dialog = new AlertDialog.Builder(MainMahasiswa.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainMahasiswa.this,
                                        TambahMahasiswa.class);
                                intent.putExtra("mhsid", list.get(position).mhsid);
                                startActivity(intent);
                                break;
                            case 1:
                                Mahasiswa mahasiswa = list.get(position);
                                database.mahasiswaDao().delete(mahasiswa);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerViewMhs.setLayoutManager(layoutManager);
        recyclerViewMhs.setAdapter(mahasiswaAdapter);
        btnTambahMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMahasiswa.this, TambahMahasiswa.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.mahasiswaDao().getALl());
        mahasiswaAdapter.notifyDataSetChanged();
    }
}