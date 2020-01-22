package com.example.firstandroidapplication.chats;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.API.ConfigRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.chats.DataAdapterChats.usernameChat;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class FragmentMessages extends Fragment {

    private TextView nameUserDialog;
    private View view;
    private RecyclerView listMessages;
    private DataAdapterMessages adapterMessages;
    private LinearLayoutManager linearLayoutManager;
    List<Message> post;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.messages_content, container, false);

        listMessages = view.findViewById(R.id.listMessages);

        final Button message = view.findViewById(R.id.sendMessage);

        final TextView problem = view.findViewById(R.id.problem);

         post = new ArrayList<>();

        adapterMessages = new DataAdapterMessages(getContext(), post);
        //updateChat();


        periodicUpdate.run();




        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textMessage = view.findViewById(R.id.messageTextContent);
                String textMes = textMessage.getText().toString();

                if (textMes.isEmpty()) {
                    textMessage.setHint("Empty message");
                } else {
                    Message message1 = new Message();
                    message1.setText(textMes);

                    message1.setMsgTo(usernameChat.getUsername());

                    ConfigRetrofit.getInstance()
                            .sendMessage(token, message1)
                            .enqueue(new Callback<Object>() {

                                @Override
                                public void onResponse(Call<Object> call, Response<Object> response) {

                                    if (!response.isSuccessful()) {
                                        problem.setText("Проблемы с авторизацией");
                                    }
                                    else {
                                        adapterMessages.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Object> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });

                    textMessage.setText("");
                    textMessage.setHint("Your text");

                }
            }

        });

        return  view;
    }


    public void updateChat(){
        ConfigRetrofit.getInstance()
                .getMessages(token, usernameChat.getUsername())
                .enqueue(new Callback<List<Message>>() {
                    @Override
                    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                        if(response.isSuccessful()) {
                             post = response.body();

                            nameUserDialog = view.findViewById(R.id.nameUser);

                            nameUserDialog.setText(usernameChat.getSurname() + " " + usernameChat.getFirstname());
                            adapterMessages.setMessagesList(post);


                            listMessages.setAdapter(adapterMessages);

                        }
                        else {

                            //problem.setText("Проблемы с авторизацией");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {

                        t.printStackTrace();
                    }
                });
    }

    Handler handler = new Handler();
    private Runnable periodicUpdate = new Runnable () {
        @Override
        public void run() {
            // scheduled another events to be in 10 seconds later
            handler.postDelayed(periodicUpdate, 1000);
                    // below is whatever you want to do

            if(view.hasFocusable()){
                updateChat();
            }


        }
    };

}
