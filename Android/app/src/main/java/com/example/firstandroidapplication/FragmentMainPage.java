package com.example.firstandroidapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstandroidapplication.chats.FragmentChats;
import com.example.firstandroidapplication.users.FragmentContacts;

public class FragmentMainPage extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_page_content, container, false);

        getActivity().setTitle("Messenger");

        Button myContacts = view.findViewById(R.id.openMyContacts);
        Button myChats = view.findViewById(R.id.openMyChats);


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
