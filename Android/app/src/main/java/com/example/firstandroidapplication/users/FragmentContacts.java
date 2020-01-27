package com.example.firstandroidapplication.users;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.contacts_content, container, false);

        getActivity().setTitle("Contacts");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = view.findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fTrans;

                FragmentNewContact fragmentNewContact = new FragmentNewContact();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.main, fragmentNewContact);
                fTrans.addToBackStack(null);
                fTrans.commit();
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
        inflater.inflate(R.menu.menu_main, menu);
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }





}
