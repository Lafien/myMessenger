package com.example.firstandroidapplication.chats;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;
import static com.example.firstandroidapplication.chats.DataAdapterChats.usernameChat;

public class FragmentMessages extends Fragment {

    private TextView nameUserDialog;
    private View view;
    private RecyclerView listMessages;
    private DataAdapterMessages adapterMessages;
    List<Message> post;
    List<Message> postNew;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.messages_content, container, false);

        listMessages = view.findViewById(R.id.listMessages);

        final Button message = view.findViewById(R.id.sendMessage);

        final TextView problem = view.findViewById(R.id.problem);

         post = new ArrayList<>();
         postNew = new ArrayList<>();

        adapterMessages = new DataAdapterMessages(getContext(), post);


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

                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {

                        t.printStackTrace();
                    }
                });

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
                                        problem.setText("Problem with authorization");
                                    }
                                    else {
                                        //adapterMessages.notifyDataSetChanged();
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
                            postNew = response.body();


                            if(postNew.size()>post.size()){
                                Message buf = postNew.get(postNew.size()-1);
                                post.add(buf);
                                adapterMessages.notifyItemInserted(post.size());


                                LinearLayoutManager layoutManager = (LinearLayoutManager) listMessages.getLayoutManager();

                                int totalItemCount = layoutManager.getItemCount();
                                int lastVisible = layoutManager.findLastVisibleItemPosition();
                                boolean endHasBeenReached = lastVisible + 2 >= totalItemCount;

                                if (totalItemCount > 0 && endHasBeenReached) {
                                    listMessages.smoothScrollToPosition(post.size());
                                }
                            }
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
            handler.postDelayed(periodicUpdate, 1000);

            if(view.hasFocusable()){
                updateChat();
            }

        }
    };

}
