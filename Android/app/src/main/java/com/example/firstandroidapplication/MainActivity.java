package com.example.firstandroidapplication;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstandroidapplication.authorization.FragmentUserAuthorization;
import com.example.firstandroidapplication.users.FragmentUserMyProfile;


public class MainActivity extends AppCompatActivity  {
    public static ActionBar actionBar;
    AlertDialog.Builder ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(0,153,203)));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.main, new FragmentUserAuthorization());
        fragmentTransaction.commit();


        ad = new AlertDialog.Builder(this);
        ad.setTitle("Warning");
        ad.setMessage("Do you want to close the app?");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                finish();
            }
        });

        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });



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

                ad.show();
            }
            else {
                onBackPressed();
            }
        }

        if(id == R.id.action_search){
            FragmentTransaction fTrans;

            FragmentUserMyProfile fragmentUserMyProfile = new FragmentUserMyProfile();

            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.main, fragmentUserMyProfile);
            fTrans.addToBackStack(null);
            fTrans.commit();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                ad.show();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
