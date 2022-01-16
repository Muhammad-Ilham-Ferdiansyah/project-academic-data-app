package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Jurusan;

public class TambahJurusan extends AppCompatActivity {
    private EditText editNameJrsn, editJenjang;
    private Button btnSaveJrsn;
    private AppDatabase database;
    private int jrsnid = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jurusan);
        editNameJrsn = findViewById(R.id.namajrsn);
        editJenjang  = findViewById(R.id.jenjang);
        btnSaveJrsn  = findViewById(R.id.btn_savejrsn);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        jrsnid = intent.getIntExtra("jrsnid", 0);
        if (jrsnid>0){
            isEdit = true;
            Jurusan jurusan = database.jurusanDao().get(jrsnid);
            editNameJrsn.setText(jurusan.nama_jurusan);
            editJenjang.setText(jurusan.jenjang);
        }else{
            isEdit = false;
        }
        btnSaveJrsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.jurusanDao().update(jrsnid, editJenjang.getText().toString(),
                            editNameJrsn.getText().toString());
                }else{
                    database.jurusanDao().insertAll(editJenjang.getText().toString(),
                            editNameJrsn.getText().toString());
                }
                finish();
            }
        });
    }
}