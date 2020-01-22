package com.example.firstandroidapplication.authorization;

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
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstandroidapplication.FragmentMainPage;
import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.API.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentUserAuthorization extends Fragment {

    public static String token = "";
    public static String authUser = "";
    //shared preference

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.authorization_content, container, false);

        Button authorization = view.findViewById(R.id.send);


        final EditText editText = view.findViewById(R.id.login);
        final EditText editText1 = view.findViewById(R.id.password);
        final TextView textView = view.findViewById(R.id.textView2);


        authorization.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                UserAuthorization userAuthorization = new UserAuthorization();


                String login = editText.getText().toString();


                String password = editText1.getText().toString();

                userAuthorization.setUsername(login);
                userAuthorization.setPassword(password);


                ConfigRetrofit.getInstance()
                        .authorizationUser(userAuthorization)
                        .enqueue(new Callback<UserSecurity>() {
                            @Override
                            public void onResponse(Call<UserSecurity> call, Response<UserSecurity> response) {

                                if(response.isSuccessful()) {
                                    UserSecurity post = response.body();

                                    token = "Bearer_" + post.getToken();
                                    authUser = post.getUsername();

                                    FragmentTransaction fTrans;

                                    FragmentMainPage fragmentMainPage = new FragmentMainPage();

                                    fTrans = getFragmentManager().beginTransaction();
                                    fTrans.replace(R.id.main, fragmentMainPage);
                                    fTrans.addToBackStack(null);
                                    fTrans.commit();

                                }
                                else {
                                    textView.setText("Incorrect login or password");
                                }
                            }

                            @Override
                            public void onFailure(Call<UserSecurity> call, Throwable t) {
                                textView.setText("No connection to server");
                                t.printStackTrace();
                            }
                        });



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
