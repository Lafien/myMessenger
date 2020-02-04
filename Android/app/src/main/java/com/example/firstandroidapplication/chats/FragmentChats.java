package com.example.firstandroidapplication.chats;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.authorization.FragmentUserAuthorization;
import com.example.firstandroidapplication.users.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.MainActivity.actionBar;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class FragmentChats extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.chats_content, container, false);


        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);


        ConfigRetrofit.getInstance()
                .getChats(token)
                .enqueue(new Callback<List<UserInfo>>() {
                    @Override
                    public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {

                        if(response.isSuccessful()) {
                            final List<UserInfo> chats = response.body();


                            RecyclerView recyclerView =  view.findViewById(R.id.list);
                            DataAdapterChats adapter = new DataAdapterChats(getContext(), chats);
                            recyclerView.setAdapter(adapter);

                        }
                        else
                        {
                            Toast toast = Toast.makeText(getActivity(), "JWT token is expired or invalid",  Toast.LENGTH_LONG);
                            toast.show();

                            AppCompatActivity activity = (AppCompatActivity) view.getContext();

                            FragmentUserAuthorization fragmentUserAuthorization = new FragmentUserAuthorization();

                            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentUserAuthorization);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                        Toast toast = Toast.makeText(getActivity(), "No response from server",  Toast.LENGTH_LONG);
                        toast.show();

                        t.printStackTrace();
                    }
                });

        setHasOptionsMenu(true);
        return  view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setTitle("Chats");
        super.onCreateOptionsMenu(menu, inflater);
    }

}
