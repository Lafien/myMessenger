package com.example.firstandroidapplication.users;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.FragmentMainPage;
import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.authorization.UserAuthorization;
import com.example.firstandroidapplication.authorization.UserSecurity;

import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNewContact extends Fragment {


    //shared preference

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_contact_content, container, false);

        Button addContact = view.findViewById(R.id.addContact);


        final EditText editText = view.findViewById(R.id.newContact);
        final TextView textView = view.findViewById(R.id.textView3);


        addContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final UserInfo userInfo = new UserInfo();

                String login = editText.getText().toString();

                userInfo.setUsername(login);


                ConfigRetrofit.getInstance()
                        .addContact(token, userInfo)
                        .enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {

                                if (response.isSuccessful()) {

                                    textView.setText("User was added");

                                    FragmentTransaction fTrans;

                                    FragmentContacts fragmentContacts = new FragmentContacts();

                                    fTrans = getFragmentManager().beginTransaction();
                                    fTrans.replace(R.id.main, fragmentContacts);
                                    fTrans.addToBackStack(null);
                                    fTrans.commit();

                                }
                                else
                                    {
                                        textView.setText("Проблемы с авторизацией");
                                }

                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });



            }



        });


        setHasOptionsMenu(true);

        return  view;
    }

}
