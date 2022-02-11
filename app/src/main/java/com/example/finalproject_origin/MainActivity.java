package com.example.finalproject_origin;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;


import com.example.finalproject_origin.ui.HomeFragment;
import com.example.finalproject_origin.ui.RegistFragment;
import com.example.finalproject_origin.ui.SurfingFragment;
import com.example.finalproject_origin.ui.UserCenterFragment;
import com.example.finalproject_origin.ui.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private BottomNavigationView navView;
    private Fragment currentFraggment;
    private HomeFragment homeFragment;
    private SurfingFragment surfingFragment;
    private RegistFragment registFragment;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private UserFragment userFragment;
    private UserCenterFragment userCenterFragment;
    private FirebaseDatabase firebaseControl;
    private DatabaseReference dataReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseControl = FirebaseDatabase.getInstance();
        dataReference = firebaseControl.getReference().child("customer");

        fragmentManager = getSupportFragmentManager();

        homeFragment = HomeFragment.newInstance("test", "test");
        surfingFragment = SurfingFragment.newInstance("", "");
        userFragment = UserFragment.newInstance("", "");
        registFragment = RegistFragment.newInstance("","");
        userCenterFragment = UserCenterFragment.newInstance("","");


//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_main, surfingFragment, "surf")
//                .add(R.id.fragment_main, userFragment, "user")
//                .hide(surfingFragment)
//                .hide(userFragment)
//                .replace(R.id.fragment_main, homeFragment,"home")
//                .commit();


        navView = findViewById(R.id.nav_view);
        getSupportActionBar().setTitle("home");

        //FIXME: navhostManager have error, can't find the NavManager, need to be solve to
        // replace the setOnItemSelectedListener
//        appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dash, R.id.navigation_user)
//                .build();

//        navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_main);
//        navController = Navigation.findNavController(this, R.id.fragment_main);
//        navController = navHostFragment.getNavController();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);


        navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.navigation_home:
                    getSupportActionBar().setTitle("home");
                    startFragment(homeFragment,"home");


                    break;
                case R.id.navigation_dash:
                    getSupportActionBar().setTitle("surfing");
                    startFragment(surfingFragment,"home");
                    break;

                case R.id.navigation_user:
                    getSupportActionBar().setTitle("User");
                    startFragment(userFragment,"home");
                    break;
            }

            return true;
        });
    }

    private void startFragment(Fragment fragment,String tag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main,fragment).commit();
    }


    public void showRegistFragment() {
        startFragment(registFragment, "regist");

    }
}