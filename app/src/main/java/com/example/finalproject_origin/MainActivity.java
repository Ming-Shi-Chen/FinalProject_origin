package com.example.finalproject_origin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dash, R.id.navigation_user)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.fragmentContainer);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();

                    break;
                case R.id.navigation_dash:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new SurfingFragment()).commit();
                    break;

                case R.id.navigation_user:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new UserFragment()).commit();
                    break;
            }

            return true;
        });
    }
}