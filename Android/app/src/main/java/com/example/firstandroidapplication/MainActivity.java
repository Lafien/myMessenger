package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count1 = 0;
    int count2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view){
        TextView textView = findViewById(R.id.message1);
        textView.setText("Теперь нажато");
    }



    public void click2(View view){

        TextView textView = findViewById(R.id.message2);
        count1++;
        textView.setText("Кнопка нажата " + count1 + " раз");
    }

    public void click3(View view){
        TextView textView = findViewById(R.id.message3);
        count2++;
        if(count1==count2){
            textView.setText("Нажатия равны!");
        } else {
            textView.setText("Кнопка нажата " + count2 + " раз. Чтобы догнать вторую кнопку, нужно нажать еще " + (count1 - count2) + " раз");
        }
    }


}
