package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Window;
import android.view.WindowManager;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,mobile,password;
    Button buttonRegister;

    SharedPreferences sharedPreferences;
    //create a shared preferences name and also create a key name
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_PASSWORD = "password";

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();


        name = findViewById(R.id.etName);
        email    = findViewById(R.id.input_email);
        mobile = findViewById(R.id.etMobile);
        password   = findViewById(R.id.input_password);

        buttonRegister = findViewById(R.id.buttonRegister);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);


        //when open activity first check sharedpreferences data available or not
        String names = sharedPreferences.getString(KEY_NAME,null);
        if (name == null){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }




     buttonRegister.setOnClickListener(new View.OnClickListener() {
    public void onClick(View view){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAME,name.getText().toString());
            editor.putString(KEY_EMAIL,email.getText().toString());
            editor.putString(KEY_MOBILE,mobile.getText().toString());
            editor.putString(KEY_PASSWORD,password.getText().toString());
            editor.apply();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);

            Toast.makeText(RegisterActivity.this,"Registration Successfully!", Toast.LENGTH_SHORT).show();
        }
    });

}
}