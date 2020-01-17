package com.example.firstandroidapplication;

import android.app.Fragment;
import android.os.Bundle;
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

import com.example.firstandroidapplication.model.Message;
import com.example.firstandroidapplication.service.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;
import static com.example.firstandroidapplication.FragmentChats.userChat;
import static com.example.firstandroidapplication.FragmentUserAuthorization.token;
import static com.example.firstandroidapplication.FragmentChats.usernameChat;

public class FragmentMessages extends Fragment {

    TextView nameUserDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.messages_content, container, false);



        final Button message = view.findViewById(R.id.sendMessage);


        final TextView problem = view.findViewById(R.id.problem);

        NetworkService.getInstance()
                .getMessages()
                .getMessages(token, usernameChat)
                .enqueue(new Callback<List<Message>>() {
                    @Override
                    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                        if(response.isSuccessful()) {
                            List<Message> post = response.body();

                            nameUserDialog = view.findViewById(R.id.nameUser);

                            nameUserDialog.setText(userChat.getSurname() + " " + userChat.getFirstname());

                            ListView countriesList = view.findViewById(R.id.contactsList);

                            ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, post);

                            countriesList.setAdapter(adapter);
                            adapter.setNotifyOnChange(true);
                        }
                        else {

                            problem.setText("Проблемы с авторизацией");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {


                        t.printStackTrace();
                    }
                });






        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textMessage = view.findViewById(R.id.messageText);
                String textMes = textMessage.getText().toString();

                if (textMes.isEmpty()) {
                    textMessage.setHint("Пустое сообщение");
                } else {
                    Message message1 = new Message();
                    message1.setText(textMes);

                    message1.setMsgTo(usernameChat);

                    NetworkService.getInstance()
                            .sendMessage()
                            .sendMessage(token, message1)
                            .enqueue(new Callback<Object>() {

                                @Override
                                public void onResponse(Call<Object> call, Response<Object> response) {

                                    if (!response.isSuccessful()) {
                                        problem.setText("Проблемы с авторизацией");
                                    }
                                }

                                @Override
                                public void onFailure(Call<Object> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });

                    textMessage.setText("");
                    textMessage.setHint("Текст сообщения");

                }
            }

        });

        return  view;
    }


}
