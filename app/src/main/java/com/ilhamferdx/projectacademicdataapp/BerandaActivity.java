package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BerandaActivity extends AppCompatActivity {

    Button button_logout;
    CardView btnMahasiswa, btnDosen, btnMatkul, btnJurusan;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        button_logout = findViewById(R.id.logout);
        btnDosen      = findViewById(R.id.data_dosen);
//        btnJurusan    = findViewById(R.id.data_jurusan);
//        btnMatkul     = findViewById(R.id.data_matkul);
//        btnMahasiswa  = findViewById(R.id.data_mahasiswa);

//        Aksi setiap Card
        btnDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, MainDosen.class);
                startActivity(intent);
            }
        });

//        btnMatkul.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BerandaActivity.this, MainMatkul.class);
//                startActivity(intent);
//            }
//        });
//
//        btnJurusan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BerandaActivity.this, MainJurusan.class);
//                startActivity(intent);
//            }
//        });
//
//        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BerandaActivity.this, MainMahasiswa.class);
//                startActivity(intent);
//            }
//        });


        //sahred preferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);
        String email = sharedPreferences.getString(KEY_EMAIL,null);

        // Aksi Logout

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(BerandaActivity.this,"Log out successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BerandaActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}