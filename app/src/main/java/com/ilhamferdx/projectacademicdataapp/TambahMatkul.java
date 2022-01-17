package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Matkul;

public class TambahMatkul extends AppCompatActivity {

    private EditText editNameMatkul, editKodeMatkul, editSks;
    private Button btnSaveMatkul;
    private AppDatabase database;
    private int matkulid = 0;
    private boolean isEdit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_matkul);
        editKodeMatkul  = findViewById(R.id.kode_matkul);
        editNameMatkul  = findViewById(R.id.nama_matkul);
        editSks         = findViewById(R.id.sks);
        btnSaveMatkul   = findViewById(R.id.btn_savematkul);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        matkulid = intent.getIntExtra("matkulid", 0);

        if (matkulid>0){
            setTitle("Edit Data Mata Kuliah");
            isEdit = true;
            Matkul matkul = database.matkulDao().get(matkulid);
            editKodeMatkul.setText(matkul.kode_matkul);
            editNameMatkul.setText(matkul.nama_matkul);
            editSks.setText(String.valueOf(matkul.sks));
        }else{
            setTitle("Tambah Data Mata Kuliah");
            isEdit = false;
        }
        btnSaveMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.matkulDao().update(matkulid, editKodeMatkul.getText().toString(),
                            editNameMatkul.getText().toString(),
                            Integer.parseInt(editSks.getText().toString())); //ini bor kalo error liat kesini
                }else{
                    database.matkulDao().insertAll(editKodeMatkul.getText().toString(),
                            editNameMatkul.getText().toString(),
                            Integer.parseInt(editSks.getText().toString())); //check bor
                }
                finish();
            }
        });
    }
}