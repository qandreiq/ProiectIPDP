package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckPassWordActivity extends AppCompatActivity {

    private EditText userNameCheck;
    private EditText passWordCheck;
    private Button checkButton;
    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pass_word);



        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(32, 32, 32));

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getString("username");
        userNameCheck = findViewById(R.id.checkUser);
        passWordCheck = findViewById(R.id.checkPassWord);
        checkButton = findViewById(R.id.checkButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper(CheckPassWordActivity.this);
                if (currentUser.equals(userNameCheck.getText().toString())) {
                    if (!db.validateLogin(userNameCheck.getText().toString(), passWordCheck.getText().toString())) {
                        Toast.makeText(CheckPassWordActivity.this, "Please enter your correct credentials!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CheckPassWordActivity.this, "Check successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CheckPassWordActivity.this, EditUserActivity.class);
                        intent.putExtra("username", userNameCheck.getText().toString());
                        startActivity(intent);
                    }
                }
                else Toast.makeText(CheckPassWordActivity.this,"Wrong username!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}