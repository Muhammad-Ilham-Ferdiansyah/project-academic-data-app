package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Mahasiswa;

public class TambahMahasiswa extends AppCompatActivity {

    private EditText editNameMhs, editNpm;
    private Button btnSaveMhs;
    private AppDatabase database;
    private int mhsid = 0;
    private boolean isEdit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);
        editNameMhs  = findViewById(R.id.nama_mhs);
        editNpm      = findViewById(R.id.npm);
        btnSaveMhs   = findViewById(R.id.btn_savemhs);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
                mhsid = intent.getIntExtra("mhsid", 0);

        if (mhsid>0){
            setTitle("Edit Data Mahasiswa");
            isEdit = true;
            Mahasiswa mahasiswa = database.mahasiswaDao().get(mhsid);
            editNameMhs.setText(mahasiswa.nama_mahasiswa);
            editNpm.setText(String.valueOf(mahasiswa.npm));
        }else{
            setTitle("Tambah Data Mahasiswa");
            isEdit = false;
        }
        btnSaveMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.mahasiswaDao().update(mhsid, editNameMhs.getText().toString(),
                            Integer.parseInt(editNpm.getText().toString()));
                }else{
                    database.mahasiswaDao().insertAll(editNameMhs.getText().toString(),
                            Integer.parseInt(editNpm.getText().toString()));
                }
                finish();
            }
        });
    }
}