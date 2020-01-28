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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.firstandroidapplication.FragmentMainPage;
import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.API.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.MainActivity.actionBar;

public class FragmentUserAuthorization extends Fragment {

    public static String token = "";
    public static String authUser = "";
    AuthorisationViewModel model;
    //shared preference

    EditText editText;
    EditText editText1;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.authorization_content, container, false);


        //getActivity().setTitle("Messenger");
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        Button authorization = view.findViewById(R.id.send);




        editText = view.findViewById(R.id.login);
        editText1 = view.findViewById(R.id.password);
        textView = view.findViewById(R.id.textView2);


        authorization.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                String login = editText.getText().toString();


                String password = editText1.getText().toString();

                UserAuthorization userAuthorization = new UserAuthorization(login, password);



                model = ViewModelProviders.of((FragmentActivity) getActivity(), new ModelFactoryAuthorization(userAuthorization)).get(AuthorisationViewModel.class);

                LiveData<UserSecurity> data = model.getData();
                data.observe((FragmentActivity) getActivity(), new Observer<UserSecurity>() {

                    @Override
                    public void onChanged(UserSecurity userSecurity) {
                        token = "Bearer_" + userSecurity.getToken();
                        authUser = userSecurity.getUsername();
                        textView.setText(userSecurity.getToken());
                    }
                });


                /*ConfigRetrofit.getInstance()
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
                        });*/



            }



        });

        setHasOptionsMenu(true);

        return  view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        menu.clear();


        super.onCreateOptionsMenu(menu, inflater);
    }
}
