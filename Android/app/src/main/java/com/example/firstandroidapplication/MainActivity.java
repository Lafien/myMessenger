package com.example.firstandroidapplication;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstandroidapplication.authorization.FragmentUserAuthorization;
import com.example.firstandroidapplication.users.FragmentUserMyProfile;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FragmentTransaction tran = getFragmentManager().beginTransaction();
        tran.add(R.id.main, new FragmentUserAuthorization());
        tran.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                super.onBackPressed();
            }
            else {
                getFragmentManager().popBackStackImmediate();
            }
        }

        if(id == R.id.action_search){
            FragmentTransaction fTrans;

            FragmentUserMyProfile fragmentUserMyProfile = new FragmentUserMyProfile();

            fTrans = getFragmentManager().beginTransaction();
            fTrans.replace(R.id.main, fragmentUserMyProfile);
            fTrans.addToBackStack(null);
            fTrans.commit();
        }


        return super.onOptionsItemSelected(item);
    }
}
