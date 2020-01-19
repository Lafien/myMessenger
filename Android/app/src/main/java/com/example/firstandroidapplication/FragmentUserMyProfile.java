package com.example.firstandroidapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import com.example.firstandroidapplication.model.UserInfo;
import com.example.firstandroidapplication.service.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.FragmentUserAuthorization.token;

public class FragmentUserMyProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_profile_content, container, false);

        final TextView textView =  view.findViewById(R.id.username);
        final TextView textView1 =  view.findViewById(R.id.surname);
        final TextView textView2 =  view.findViewById(R.id.firstname);
        final TextView problem = view.findViewById(R.id.problem);

        NetworkService.getInstance()
                .myProfile(token)
                .enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                        if(response.isSuccessful()) {
                            UserInfo post = response.body();

                            textView.setText(post.getUsername());
                            textView1.setText(post.getSurname());
                            textView2.setText(post.getFirstname());
                        }
                        else {
                            textView.setText("");
                            textView1.setText("");
                            textView2.setText("");
                            problem.setText("Проблемы с авторизацией");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {

                        textView.setText("Error occurred while getting request!");
                        textView1.setText("");
                        textView2.setText("");
                        t.printStackTrace();
                    }
                });

        return  view;
    }


}
