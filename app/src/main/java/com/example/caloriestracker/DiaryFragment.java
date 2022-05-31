package com.example.caloriestracker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DiaryFragment extends Fragment {

    private String currentUser;
    private TextView remainingCalories;
    private EditText foodName;
    private EditText foodQuantity;
    private Button addButton;
    private String foodEntered;
    private int quantityEntered = -1;
    private FoodModel selectedFood;
    private int recalculatedCalories;
    private int calculatedCalories;
    private ListView foodView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.rgb(32, 32, 32));

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = currentDate.format(c);

        DataBaseHelper db = new DataBaseHelper(getActivity());
        DataBaseEatenFood dbEF = new DataBaseEatenFood(getActivity());

        FoodModel apple = new FoodModel("apple", 50);
        FoodModel banana = new FoodModel("banana", 90);
        FoodModel ham = new FoodModel("ham", 150);

        Bundle data = getArguments();
        currentUser = "null";
        if (data != null)
            currentUser = data.getString("usernameFragment");

        remainingCalories = view.findViewById(R.id.remainingCalories);
        foodName = view.findViewById(R.id.foodName);
        foodQuantity = view.findViewById(R.id.foodQuantity);
        addButton = view.findViewById(R.id.addFoodButton);
        foodView = view.findViewById(R.id.foodView);

        if (dbEF.getEatenCalories(currentUser, formattedDate) > 0) {
            calculatedCalories = db.getGoal(currentUser) - dbEF.getEatenCalories(currentUser, formattedDate);
            showFood(dbEF.getEverything(currentUser, formattedDate));
        } else calculatedCalories = db.getGoal(currentUser);

        setColor(calculatedCalories);

        remainingCalories.setText(String.valueOf(calculatedCalories));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(foodName.getText().toString().isEmpty() || foodQuantity.getText().toString().isEmpty())) {
                    foodEntered = foodName.getText().toString();
                    if (foodEntered.toLowerCase().equals("apple"))
                        try {
                            selectedFood = (FoodModel) apple.clone();
                        } catch (CloneNotSupportedException c) {
                        }
                    if (foodEntered.toLowerCase().equals("banana"))
                        try {
                            selectedFood = (FoodModel) banana.clone();
                        } catch (CloneNotSupportedException c) {
                        }
                    if (foodEntered.toLowerCase().equals("ham"))
                        try {
                            selectedFood = (FoodModel) ham.clone();
                        } catch (CloneNotSupportedException c) {
                        }

                    quantityEntered = Integer.valueOf(foodQuantity.getText().toString());
                    if (selectedFood != null) {
                        dbEF.addOne(currentUser, selectedFood, formattedDate, quantityEntered);
                        Toast.makeText(getActivity(), "Food added", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getActivity(), "Food unavailable/Incorrect data", Toast.LENGTH_SHORT).show();

                recalculatedCalories = db.getGoal(currentUser) - dbEF.getEatenCalories(currentUser, formattedDate);
                setColor(recalculatedCalories);
                remainingCalories.setText(String.valueOf(recalculatedCalories));
                showFood(dbEF.getEverything(currentUser,formattedDate));
            }
        });
    }

    private void setColor(int calories) {
        if (calories > 1000)
            remainingCalories.setTextColor(Color.GREEN);

        if (calories < 1000 && calculatedCalories > 500)
            remainingCalories.setTextColor(Color.YELLOW);

        if (calories < 500)
            remainingCalories.setTextColor(Color.RED);
    }

    private void showFood(List<FoodModel> list) {
        ArrayAdapter foodAdapter = new ArrayAdapter<FoodModel>(getActivity(), android.R.layout.simple_list_item_1, list);
        foodView.setAdapter(foodAdapter);

    }

}
