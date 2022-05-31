package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GoalCalculatorActivity extends AppCompatActivity {

    private TextView calculatorText;
    private EditText weightBox;
    private EditText heightBox;
    private EditText ageBox;
    private Button calculateButton;
    private TextView newGoalText;
    private TextView dailyGoalText;
    private Button nextButton;
    private Spinner activityBox;
    private Spinner goalBox;
    private boolean checkCalculated;
    private String userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_calculator);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(32, 32, 32));

        calculatorText = findViewById(R.id.calculatorText);
        weightBox = findViewById(R.id.weightBox);
        heightBox = findViewById(R.id.heightBox);
        ageBox = findViewById(R.id.ageBox);
        calculateButton = findViewById(R.id.calculateButton);
        newGoalText = findViewById(R.id.newGoalText);
        dailyGoalText = findViewById(R.id.dailyGoalText);
        nextButton = findViewById(R.id.nextButton);
        activityBox = findViewById(R.id.activityBox);
        goalBox = findViewById(R.id.goalBox);

        calculatorText.setGravity(Gravity.CENTER);
        newGoalText.setGravity(Gravity.CENTER);
        dailyGoalText.setGravity(Gravity.CENTER);

        checkCalculated = false;

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper db = new DataBaseHelper(GoalCalculatorActivity.this);

                if (!(weightBox.getText().toString().isEmpty() || heightBox.getText().toString().isEmpty() || ageBox.getText().toString().isEmpty())) {
                    double weightInput = Double.valueOf(weightBox.getText().toString());
                    double heightInput = Double.valueOf(heightBox.getText().toString());
                    double ageInput = Double.valueOf(ageBox.getText().toString());
                    double activityLevel = 0;
                    double goal = 0;
                    String activityLevelInput = activityBox.getSelectedItem().toString();
                    String goalInput = goalBox.getSelectedItem().toString();

                    switch (goalInput) {
                        case "Lose weight":
                            goal = 0.85;
                            break;
                        case "Maintain weight":
                            goal = 1;
                            break;
                        case "Gain weight":
                            goal = 1.15;
                            break;
                    }

                    switch (activityLevelInput) {
                        case "Sedentary":
                            activityLevel = 1.2;
                            break;
                        case "Moderately Active":
                            activityLevel = 1.55;
                            break;
                        case "Extremely Active":
                            activityLevel = 1.9;
                            break;
                    }

                    if (!(activityLevel == 0 || goal == 0)) {

                        Bundle extras = getIntent().getExtras();
                        userGender = db.getGender(extras.getString("username"));
                        double AMR = calculateBMR(weightInput, heightInput, ageInput,userGender) * activityLevel;
                        newGoalText.setText(extras.getString("username") + ", your new daily goal is");
                        dailyGoalText.setTextColor(Color.GREEN);
                        dailyGoalText.setTextSize(50);
                        dailyGoalText.setText("" + (int) Math.round(calculateCalories(AMR, goal)) + " kcal");
                        checkCalculated = true;


                        db.setGoal(extras.getString("username"), (int) Math.round(calculateCalories(AMR, goal)));
                        db.setHeight(extras.getString("username"),(int) heightInput);
                        db.setWeight(extras.getString("username"),(int)weightInput);
                        db.setCaloricNeeds(extras.getString("username"),(int) Math.round(calculateCalories(AMR, goal)));
                    } else
                        Toast.makeText(GoalCalculatorActivity.this, "Please enter all the data", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(GoalCalculatorActivity.this, "Please enter all the data", Toast.LENGTH_SHORT).show();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if (checkCalculated) {
                    Intent intent = new Intent(GoalCalculatorActivity.this, MainActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(GoalCalculatorActivity.this, "Please calculate your caloric needs first", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GoalCalculatorActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private double calculateBMR(double weight, double height, double age,String gender) {
        if (gender.equals("Male"))
            return 5 + (10 * weight) + (6.25 * height) - (5 * age);
        else
            return (10 * weight) + (6.25 * height) - (5 * age) - 161;
    }

    private double calculateCalories(double AMR, double increase) {
        return AMR * increase;
    }

}