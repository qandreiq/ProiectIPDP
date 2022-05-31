package com.example.caloriestracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    private String currentUser;
    private TextView userName;
    private TextView age;
    private TextView gender;
    private TextView heightWeight;
    private Button changeUserNameButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_user,container,false);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserDB db = UserDB.getDBInstance(getActivity().getApplicationContext());
        view.setBackgroundColor(Color.rgb(32, 32, 32));
        Bundle data = getArguments();

        currentUser = "null";
        if(data != null)
            currentUser = data.getString("usernameFragment");

        userName = view.findViewById(R.id.userNameShow2);
        age = view.findViewById(R.id.ageShow2);
        gender = view.findViewById(R.id.genderShow2);
        heightWeight = view.findViewById(R.id.heightWeightShow2);
        changeUserNameButton = view.findViewById(R.id.changeUserNameButton2);

        userName.setText(currentUser);
        age.setText(String.valueOf(db.userDAO().getAge(currentUser)));
        gender.setText(db.userDAO().getGender(currentUser));
        heightWeight.setText(String.valueOf(db.userDAO().getHeight(currentUser))+"cm "+ String.valueOf(db.userDAO().getWeight(currentUser)) + "kg");

    }

}
