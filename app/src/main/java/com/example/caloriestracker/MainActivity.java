package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeText, welcomeText2;
    private EditText idBox;
    private EditText passwordBox;
    private CheckBox rememberCheckBox;
    private Button loginButton;
    private Button createAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(32, 32, 32));

        welcomeText = findViewById(R.id.welcometText);
        welcomeText2 = findViewById(R.id.welcomeText2);
        idBox = findViewById(R.id.idBox);
        passwordBox = findViewById(R.id.passwordBox);
        rememberCheckBox = findViewById(R.id.rememberCheckBox);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        welcomeText.setGravity(Gravity.CENTER);
        welcomeText2.setGravity(Gravity.CENTER);
        welcomeText2.setTextColor(Color.DKGRAY);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputIdBox = idBox.getText().toString().toUpperCase();
                String inputPasswordBox = passwordBox.getText().toString();

                if (inputIdBox.isEmpty() || inputPasswordBox.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your correct credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!checkCredentials(inputIdBox, inputPasswordBox)) {
                        Toast.makeText(MainActivity.this, "Please enter your correct credentials!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        intent.putExtra("username", inputIdBox.toString().toUpperCase());
                        startActivity(intent);
                    }
                }
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this,"Back button disabled!",Toast.LENGTH_SHORT).show();
    }
    private boolean checkCredentials(String userName, String password) {
        UserDB db = UserDB.getDBInstance(this.getApplicationContext());
        String realPassword = db.userDAO().getPassword(userName);
        if (realPassword != null)
            if (realPassword.equals(password)) return true;
        return false;
    }
}