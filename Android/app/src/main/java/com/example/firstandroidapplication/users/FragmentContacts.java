package com.example.firstandroidapplication.users;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.API.ConfigRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class FragmentContacts extends Fragment {
    //List<UserInfo> contacts;
    TextView problem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.contacts_content, container, false);

        problem = view.findViewById(R.id.problem);

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

                            problem.setText("Все ок");

                        }
                        else {

                            problem.setText("Проблемы с авторизацией");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserInfo>> call, Throwable t) {

                        problem.setText("Проблемы с сервером");
                        t.printStackTrace();
                    }
                });

        return  view;
    }


}
