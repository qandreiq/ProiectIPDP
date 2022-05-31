package com.example.caloriestracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private TextView userNameNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        Bundle extras = getIntent().getExtras();
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(32, 32, 32));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        userNameNavigation = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userNameNavigation);
        userNameNavigation.setText(extras.getString("username"));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            DiaryFragment diaryFragment = new DiaryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("usernameFragment", extras.getString("username"));
            diaryFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,diaryFragment).commit();
            navigationView.setCheckedItem(R.id.nav_diary);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle extras = getIntent().getExtras();
        Bundle bundle = new Bundle();
        switch (item.getItemId()){
            case R.id.nav_diary:
                DiaryFragment diaryFragment = new DiaryFragment();
                fragmentTransaction.addToBackStack(null);
                bundle.putString("usernameFragment", extras.getString("username"));
                diaryFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.fragment_container,diaryFragment).commit();
                break;
            case R.id.nav_user:
                UserFragment userFragment = new UserFragment();
                fragmentTransaction.addToBackStack(null);
                bundle.putString("usernameFragment", extras.getString("username"));
                userFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.fragment_container,userFragment).commit();
                break;
            case R.id.nav_food:
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.fragment_container,new FoodFragment()).commit();
                break;
            case R.id.nav_logout:
                Intent intent2 = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_recalculate:
                Intent intent3 = new Intent(HomePageActivity.this, GoalCalculatorActivity.class);
                intent3.putExtra("username", extras.getString("username"));
                startActivity(intent3);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else if(getSupportFragmentManager().findFragmentById(R.id.fragment_container) != null) {
            NavigationView navigationView = findViewById(R.id.nav_view);
            Bundle extras = getIntent().getExtras();
            DiaryFragment diaryFragment = new DiaryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("usernameFragment", extras.getString("username"));
            diaryFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,diaryFragment).commit();
            navigationView.setCheckedItem(R.id.nav_diary);
        }
    }
}