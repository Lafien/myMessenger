package com.example.firstandroidapplication.users;


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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.MainActivity.actionBar;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class FragmentContacts extends Fragment {

    public static FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.contacts_content, container, false);


        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                FragmentNewContact fragmentNewContact = new FragmentNewContact();

                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentNewContact);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        ConfigRetrofit.getInstance()
                .getContacts(token)
                .enqueue(new Callback<List<UserInfo>>() {
                    @Override
                    public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {

                        if(response.isSuccessful()) {
                            List<UserInfo> contacts = response.body();

                            RecyclerView recyclerView =  view.findViewById(R.id.list);
                            DataAdapterContacts adapter = new DataAdapterContacts(getContext(), contacts);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

        setHasOptionsMenu(true);
        return  view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setTitle("Contacts");
        super.onCreateOptionsMenu(menu, inflater);
    }

}
