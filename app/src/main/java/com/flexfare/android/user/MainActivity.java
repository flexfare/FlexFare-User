package com.flexfare.android.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.flexfare.android.user.fragments.Drivers;
import com.flexfare.android.user.fragments.Home;
import com.flexfare.android.user.fragments.Others;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item ->  {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = Home.newInstance();
                        break;
                    case R.id.drivers:
                        fragment = Drivers.newInstance();
                        break;
                    case R.id.navigation_notifications:
                        fragment = Others.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left, R.anim.slide_out_left);
                transaction.replace(R.id.content, fragment);
                transaction.commit();
                return true;
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, R.anim.slide_out_left);
        transaction.replace(R.id.content, Home.newInstance());
        transaction.commit();
    }
}
