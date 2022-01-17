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

import com.ilhamferdx.projectacademicdataapp.adapter.JurusanAdapter;
import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Jurusan;

import java.util.ArrayList;
import java.util.List;

public class MainJurusan extends AppCompatActivity {

    private RecyclerView recyclerViewJrsn;
    private Button btnTambahJrsn;

    private AppDatabase database;
    private JurusanAdapter jurusanAdapter;
    private List<Jurusan> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Data Jurusan");
        setContentView(R.layout.activity_main_jurusan);
        recyclerViewJrsn = findViewById(R.id.recycler_viewjrsn);
        btnTambahJrsn = findViewById(R.id.btn_tambahjrsn);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.jurusanDao().getALl());
        jurusanAdapter = new JurusanAdapter(getApplicationContext(), list);
        jurusanAdapter.setDialog(new JurusanAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit Jurusan", "Hapus Jurusan"};
                dialog = new AlertDialog.Builder(MainJurusan.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainJurusan.this,
                                        TambahJurusan.class);
                                intent.putExtra("jrsnid", list.get(position).jrsnid);
                                startActivity(intent);
                                break;
                            case 1:
                                Jurusan jurusan = list.get(position);
                                database.jurusanDao().delete(jurusan);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerViewJrsn.setLayoutManager(layoutManager);
        recyclerViewJrsn.setAdapter(jurusanAdapter);
        btnTambahJrsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainJurusan.this, TambahJurusan.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.jurusanDao().getALl());
        jurusanAdapter.notifyDataSetChanged();
    }
}