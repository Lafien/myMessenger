package com.example.firstandroidapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.firstandroidapplication.chats.FragmentChats;
import com.example.firstandroidapplication.users.FragmentContacts;
import com.google.android.material.tabs.TabLayout;

import static com.example.firstandroidapplication.MainActivity.actionBar;
import static com.example.firstandroidapplication.users.FragmentNewContact.addedNewContact;

public class FragmentMainPage extends Fragment {

    SectionsPagerAdapter adapter;
    TabLayout tabs;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_page_content, container, false);

        AppCompatActivity activity = (AppCompatActivity) view.getContext();

        activity.setTitle("Messenger");

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        viewPager = view.findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        if(addedNewContact) {
            viewPager.setCurrentItem(1, true);
            addedNewContact = false;
        }

        tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        return  view;
    }


    private void setupViewPager(ViewPager viewPager) {


        adapter = new SectionsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentChats(), "Chats");
        adapter.addFragment(new FragmentContacts(), "Contacts");
        viewPager.setAdapter(adapter);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }




}
