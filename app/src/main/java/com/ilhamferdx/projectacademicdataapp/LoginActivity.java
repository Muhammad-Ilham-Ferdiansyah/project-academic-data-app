package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.os.Build;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    EditText editText_email,editText_password;
    Button buttonLogin;
    TextView buttonRegister;
    ImageView buttonRegister2;

    SharedPreferences sharedPreferences;
//membuat constanta sharedpref
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        editText_email  = findViewById(R.id.input_email);
        editText_password = findViewById(R.id.input_password);
        buttonRegister = findViewById(R.id.Register);
        buttonRegister2 = findViewById(R.id.Register2);
        buttonLogin = findViewById(R.id.btn_Login);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);
        String email = sharedPreferences.getString(KEY_EMAIL,null);
        String password = sharedPreferences.getString(KEY_PASSWORD,null);

        if(name != null || password != null){
            //so set data on textview
            editText_email.setText(email);
            editText_password.setText(password);
        }


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValidate = editText_email.getText().toString();
                String passwordValidate = editText_password.getText().toString();

                if (emailValidate.equals(email) && passwordValidate.equals(password)){
                    Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
                    startActivity(intent);
                } else if (emailValidate.isEmpty() && passwordValidate.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill your username and password!", Toast.LENGTH_SHORT).show();
                }else if (emailValidate.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill your username!", Toast.LENGTH_SHORT).show();
                } else if (passwordValidate.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill your password!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Username or password wrong!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}