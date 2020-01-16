package com.example.firstandroidapplication;

import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction tran = getFragmentManager().beginTransaction();
        tran.add(R.id.main, new FragmentUserAuthorization());
        tran.commit();
    }

}
