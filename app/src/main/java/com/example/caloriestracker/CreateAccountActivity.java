package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText userNameInput;
    private EditText passwordInput;
    private EditText ageInput;
    private Spinner genderInput;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(32, 32, 32));

        userNameInput = findViewById(R.id.userNameCreation);
        passwordInput = findViewById(R.id.passwordCreation);
        ageInput = findViewById(R.id.ageCreation);
        genderInput = findViewById(R.id.genderCreation);
        createAccountButton = findViewById(R.id.createButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int genderCheck = 0;
                switch (genderInput.getSelectedItem().toString()) {
                    case "Gender":
                        break;
                    case "Male":
                        genderCheck = 1;
                        break;
                    case "Female":
                        genderCheck = 1;
                        break;
                }
                if (!(userNameInput.getText().toString().isEmpty() || passwordInput.getText().toString().isEmpty() || ageInput.getText().toString().isEmpty() || genderCheck == 0)) {
                    if (Integer.valueOf(ageInput.getText().toString()) > 16) {

                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String formattedDate = currentDate.format(c);

                        String username = userNameInput.getText().toString();
                        String password = passwordInput.getText().toString();
                        int age = Integer.parseInt(ageInput.getText().toString());
                        String gender = genderInput.getSelectedItem().toString();

                        createUser(username,password,age,gender,formattedDate);

                    } else
                        Toast.makeText(CreateAccountActivity.this, "You need to be at least 16yo to register!", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(CreateAccountActivity.this, "Please enter all the data!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createUser(String userName, String password, int age,String gender, String date) {
        UserDB db = UserDB.getDBInstance(this.getApplicationContext());
        List<String> currentUserNames = db.userDAO().getAllExistingUserNames();
        if (checkUniqueUserName(currentUserNames, userName.toUpperCase())) {
            UserModel user = new UserModel(userName.toUpperCase(), password, age,gender.toUpperCase(),0,0,0,date,0);
            db.userDAO().insertUser(user);
            Toast.makeText(CreateAccountActivity.this, "Account created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateAccountActivity.this, GoalCalculatorActivity.class);
            intent.putExtra("username",userName);
            startActivity(intent);
        } else
            Toast.makeText(CreateAccountActivity.this, "UserName already exists", Toast.LENGTH_SHORT).show();
    }

    private boolean checkUniqueUserName(List<String> userNamesList, String newUserName) {
        for (String userName : userNamesList)
            if (userName.equals(newUserName))
                return false;
        return true;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
        startActivity(intent);
    }
}