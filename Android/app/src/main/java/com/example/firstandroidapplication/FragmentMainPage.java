package com.example.firstandroidapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.firstandroidapplication.chats.FragmentChats;
import com.example.firstandroidapplication.users.FragmentContacts;
import com.example.firstandroidapplication.users.FragmentUserMyProfile;

public class FragmentMainPage extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_page_content, container, false);



        //Button myProfile = view.findViewById(R.id.openMyProfile);
        Button myContacts = view.findViewById(R.id.openMyContacts);
        Button myChats = view.findViewById(R.id.openMyChats);




        /*myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fTrans;

                FragmentUserMyProfile fragmentUserMyProfile = new FragmentUserMyProfile();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.main, fragmentUserMyProfile);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });*/



        myContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fTrans;

                FragmentContacts fragmentContacts = new FragmentContacts();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.main, fragmentContacts);
                fTrans.addToBackStack(null);
                fTrans.commit();

            }
        });


        myChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTrans;

                FragmentChats fragmentChats = new FragmentChats();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.main, fragmentChats);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        return  view;
    }



}
