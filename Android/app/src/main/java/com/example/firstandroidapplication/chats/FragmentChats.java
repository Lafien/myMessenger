package com.example.firstandroidapplication.chats;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.users.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class FragmentChats extends Fragment {

    public static String usernameChat = "";
    public static UserInfo userChat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.chats_content, container, false);

        getActivity().setTitle("Chats");



        final TextView problem = view.findViewById(R.id.problem);

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
                        else {

                            problem.setText("Проблемы с авторизацией");
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
        menu.clear();//например убрать все элементы меню.

        super.onCreateOptionsMenu(menu, inflater);
    }
}
