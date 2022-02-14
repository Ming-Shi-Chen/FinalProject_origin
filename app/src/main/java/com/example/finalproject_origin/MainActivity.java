package com.example.finalproject_origin;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.finalproject_origin.ui.ArticleFragment;
import com.example.finalproject_origin.ui.HomeFragment;
import com.example.finalproject_origin.ui.RegistFragment;
import com.example.finalproject_origin.ui.SurfingFragment;
import com.example.finalproject_origin.ui.SendingFragment;
import com.example.finalproject_origin.ui.UserFragment;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    private SendingFragment sendingFragment;
    private FirebaseDatabase firebaseControl;
    private DatabaseReference customerDataReference;
    private DatabaseReference articleDataReference;
    private ArrayList<Map<String, String>> customerList;
    private ArrayList<Map<String, String>> articleList;
    private ArrayList<Map<String, String>> loginArticleList;
    private ArrayList<String> homeTitleList ;

    public String loginName, loginId;
    public boolean loginFlag;
    public String articleNewId;
    public ArrayAdapter<String> adapter;
    private ArticleFragment articleFragment;
    public int articleIndex = 0;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;

        firebaseControl = FirebaseDatabase.getInstance();
        customerDataReference = firebaseControl.getReference().child("customer");
        articleDataReference = firebaseControl.getReference().child("article");

        fragmentManager = getSupportFragmentManager();

        homeFragment = HomeFragment.newInstance("test", "test");
        surfingFragment = SurfingFragment.newInstance("", "");
        userFragment = UserFragment.newInstance("", "");
        registFragment = RegistFragment.newInstance("","");
        sendingFragment = SendingFragment.newInstance("","");
        articleFragment = ArticleFragment.newInstance("","");



//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_main, surfingFragment, "surf")
//                .add(R.id.fragment_main, userFragment, "user")
//                .hide(surfingFragment)
//                .hide(userFragment)
//                .replace(R.id.fragment_main, homeFragment,"home")
//                .commit();




        navView = findViewById(R.id.nav_view);
        getSupportActionBar().setTitle("home");

        // FIXME: navhostManager have error, can't find the NavManager, need to be solve
        //  to replace the setOnItemSelectedListener

//        appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dash, R.id.navigation_user)
//                .build();
//
//        navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_main);
////        navHostFragment = NavHostFragment.create(R.navigation.bottom_navigation);
//
//
////        navController = Navigation.findNavController(this, R.id.fragment_main);
//        navController = navHostFragment.getNavController();
////        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);


        customerList = new ArrayList<>();
        articleList = new ArrayList<>();
        loginArticleList = new ArrayList<>();
        homeTitleList = new ArrayList<>();

        customerDataReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                customerList.clear();
//                try {
//                    MainActivity.this.stop(2000);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String, String> data = new HashMap<>();

                    String userId = (String) ds.child("id").getValue();
                    String userName = (String) ds.child("name").getValue();
                    String userEmail = (String) ds.child("email").getValue();
                    String userPassword = (String) ds.child("password").getValue();

                    data.put("id",userId);
                    data.put("name",userName);
                    data.put("password",userPassword);
                    data.put("email",userEmail);

                    customerList.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        articleDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                getFirebaseArticleList();
                    articleList.clear();
                    homeTitleList.clear();
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Map<String, String> data = new HashMap<>();
                        String userId = (String) ds.child("id").getValue();
                        String userName = (String) ds.child("name").getValue();
                        String title = (String) ds.child("title").getValue();
                        String content = (String) ds.child("content").getValue();

                        data.put("id",userId);
                        data.put("name",userName);
                        data.put("title",title);
                        data.put("content",content);

                        articleList.add(data);
                        homeTitleList.add(title);
                    }
                    homeTitleList.add("");

                    adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, homeTitleList);
                    articleNewId = String.valueOf(articleList.size()+1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        if( loginFlag){


        }

        // FIXME : need to put this input the valueListener
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, homeTitleList);



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

                    if (loginFlag) {
                        getSupportActionBar().setTitle("Hi "+loginName+"~~~");
                        startFragment(sendingFragment,"sending");
                    }else {
                        getSupportActionBar().setTitle("User");
                        startFragment(userFragment,"home");
                    }


                    break;
            }

            return true;
        });
    } //end of onCreate

    private void getFirebaseArticleList() {
    }

    private void startFragment(Fragment fragment,String tag){
        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.trans_in_from_left,R.anim.no_anim)
                .setCustomAnimations(R.anim.trans_in_from_left,R.anim.trans_out_to_right)
                .replace(R.id.fragment_main,fragment)
                .commit();
    }


    public void showRegistFragment() {
        startFragment(registFragment, "regist");
    }

    public void showSendingFragment(){
        getSupportActionBar().setTitle("Hi "+loginName+"~~~");
        startFragment(sendingFragment, "userCenter");
    }

    public void showArticleFragment(int i){

        articleIndex = i;
        startFragment(articleFragment,"article");
    }

    public Map<String, String> getArticle (int index) {

        Map<String, String> article = articleList.get(index);

        return article;
    }



    public boolean customerFirebaseDataCheck(String key, String value) {

        for (int i=0; i< customerList.size(); i++) {
            Map<String, String> data = customerList.get(i);
            String firebaseData = data.get(key);
            if (firebaseData.equals(value)){
                return true;
            }
        }
        return false;
    }

    public Task<Void> customerFirebaseDataSet(String id, Map<String,String> data, String name){
        Task<Void> result = customerDataReference.child(id).setValue(data);

        loginId = id;
        loginName = name;
        loginFlag = true;

        return result;
    }

    public Task<Void> articleFirebaseDataSet(Map<String, String> data){

        Task<Void> result = articleDataReference.child(articleNewId).setValue(data);

        return result;
    }

    public boolean firebaseLoginCheck(String id,String password){

        for (int i=0; i< customerList.size(); i++) {
            Map<String, String> data = customerList.get(i);
            String customerId = data.get("id");
            String customerPassword = data.get("password");

            if (customerId.equals(id)) {
                if (customerPassword.equals(password)) {
                    loginId = id;
                    loginName = data.get("name");
                    loginFlag = true;
                    return true;
                }
            }

        }
        return false;
    }


    public void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}