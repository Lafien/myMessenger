package com.example.firstandroidapplication.users;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.chats.FragmentMessages;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;
import static com.example.firstandroidapplication.users.DataAdapterContacts.chooseContactFromContacts;
import static com.example.firstandroidapplication.chats.DataAdapterChats.usernameChat;

public class FragmentUserContactsProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_profile_content, container, false);

        final TextView textView =  view.findViewById(R.id.username);
        final TextView textView1 =  view.findViewById(R.id.surname);
        final TextView textView2 =  view.findViewById(R.id.firstname);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameChat = chooseContactFromContacts;

                FragmentTransaction fTrans;

                FragmentMessages fragmentMessages = new FragmentMessages();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.main, fragmentMessages);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        ConfigRetrofit.getInstance()
                .getContactInfo(token, chooseContactFromContacts.getUsername())
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
