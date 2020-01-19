package com.example.firstandroidapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstandroidapplication.model.UserInfo;
import com.example.firstandroidapplication.service.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.FragmentUserAuthorization.token;

public class FragmentChats extends Fragment {

    public static String usernameChat = "";
    public static UserInfo userChat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.chats_content, container, false);

        //final TextView selection = view.findViewById(R.id.selected);

        final TextView problem = view.findViewById(R.id.problem);

        NetworkService.getInstance()
                .getChats(token)
                .enqueue(new Callback<List<UserInfo>>() {
                    @Override
                    public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {

                        if(response.isSuccessful()) {
                            final List<UserInfo> post = response.body();


                            ListView contactsList = view.findViewById(R.id.contactsList);
                            ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, post);
                            contactsList.setAdapter(adapter);
                            contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                                {

                                     usernameChat = post.get(position).getUsername();
                                     userChat = post.get(position);


                                    FragmentTransaction fTrans;

                                    FragmentMessages fragmentMessages = new FragmentMessages();

                                    fTrans = getFragmentManager().beginTransaction();
                                    fTrans.replace(R.id.main, fragmentMessages);
                                    fTrans.addToBackStack(null);
                                    fTrans.commit();
                                }
                            });

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


        return  view;
    }


}
