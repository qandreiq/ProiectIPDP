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
                        DataBaseHelper db = new DataBaseHelper(CreateAccountActivity.this);
                        UserModel userModel;

                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String formattedDate = currentDate.format(c);


                        try {
                            userModel = new UserModel(-1, userNameInput.getText().toString(), passwordInput.getText().toString(),
                                    Integer.parseInt(ageInput.getText().toString()), genderInput.getSelectedItem().toString(), 1, 1, 1, formattedDate, 1);
                        } catch (Exception e) {
                            Toast.makeText(CreateAccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            userModel = new UserModel(-1, "Error", "Error",
                                    -1, "Error", -1, -1, -1, "Error", -1);
                        }

                        if (!db.existingValue(userNameInput.getText().toString())) {
                            db.addOne(userModel);
                            Toast.makeText(CreateAccountActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateAccountActivity.this, GoalCalculatorActivity.class);
                            intent.putExtra("username", userNameInput.getText().toString());
                            startActivity(intent);
                        } else
                            Toast.makeText(CreateAccountActivity.this, "UserName already exists", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(CreateAccountActivity.this, "You need to be at least 16yo to register!", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(CreateAccountActivity.this, "Please enter all the data!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
        startActivity(intent);
    }
}