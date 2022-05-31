package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditUserActivity extends AppCompatActivity {

    private String currentUser;
    private TextView userName;
    private TextView age;
    private  TextView gender;
    private  TextView heightWeight;
    private EditText changeUsername;
    private EditText changePassWord;
    private Button changeUserNameButton;
    private Button changePassWordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(32, 32, 32));

        DataBaseHelper db = new DataBaseHelper(EditUserActivity.this);
        Bundle extras = getIntent().getExtras();

        currentUser = extras.getString("username");
        userName = findViewById(R.id.userNameShow);
        age = findViewById(R.id.ageShow);
        gender = findViewById(R.id.genderShow);
        heightWeight = findViewById(R.id.heightWeightShow);
        changeUsername = findViewById(R.id.changeUserName);
        changePassWord = findViewById(R.id.changePassword);
        changeUserNameButton = findViewById(R.id.changeUserNameButton);
        changePassWordButton = findViewById(R.id.changePassWordButton);

        userName.setText(currentUser);
        age.setText(String.valueOf(db.getAge(currentUser)));
        gender.setText(db.getGender(currentUser));
        heightWeight.setText(String.valueOf(db.getHeight(currentUser))+"cm "+ String.valueOf(db.getWeight(currentUser)) + "kg");

        changeUserNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.existingValue(changeUsername.getText().toString())) {
                    if (!changeUsername.getText().toString().isEmpty()) {
                        Toast.makeText(EditUserActivity.this, "UserName Changed!", Toast.LENGTH_SHORT).show();
                        db.setUserName(currentUser, changeUsername.getText().toString());
                        userName.setText(changeUsername.getText().toString());
                        Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(EditUserActivity.this, "Please enter a valid username!", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(EditUserActivity.this, "Username already taken!", Toast.LENGTH_SHORT).show();
            }
        });

        changePassWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!changePassWord.getText().toString().isEmpty()){
                        Toast.makeText(EditUserActivity.this,"PassWord Changed!",Toast.LENGTH_SHORT).show();
                        db.setPassWord(userName.getText().toString(),changePassWord.getText().toString());
                        Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else Toast.makeText(EditUserActivity.this,"Please enter a valid password!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditUserActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}