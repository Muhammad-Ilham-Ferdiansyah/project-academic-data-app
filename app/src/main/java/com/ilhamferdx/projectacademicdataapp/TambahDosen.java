package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ilhamferdx.projectacademicdataapp.database.AppDatabase;
import com.ilhamferdx.projectacademicdataapp.database.entity.Dosen;

public class TambahDosen extends AppCompatActivity {
    private EditText editNameDsn, editNip;
    private Button btnSaveDsn;
    private AppDatabase database;
    private int dsnid = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_dosen);
        editNameDsn = findViewById(R.id.full_namedsn);
        editNip = findViewById(R.id.nip);
        btnSaveDsn = findViewById(R.id.btn_savedsn);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        dsnid = intent.getIntExtra("dsnid", 0);
        if (dsnid>0){
            isEdit = true;
            Dosen dosen = database.dosenDao().get(dsnid);
            editNip.setText(dosen.nip);
            editNameDsn.setText(dosen.nama_dosen);
        }else{
            isEdit = false;
        }
        btnSaveDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.dosenDao().update(dsnid, editNameDsn.getText().toString(),
                            editNip.getText().toString());
                }else{
                    database.dosenDao().insertAll(editNameDsn.getText().toString(),
                            editNip.getText().toString());
                }
                finish();
            }
        });
    }
}