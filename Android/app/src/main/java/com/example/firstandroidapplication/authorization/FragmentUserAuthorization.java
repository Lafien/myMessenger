package com.example.firstandroidapplication.authorization;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.firstandroidapplication.FragmentMainPage;
import com.example.firstandroidapplication.R;

import static com.example.firstandroidapplication.MainActivity.actionBar;

public class FragmentUserAuthorization extends Fragment {

    public static String token = "";
    public static String authUser = "";

    EditText editText;
    EditText editText1;

    AuthorizationViewModel model;

    public static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.authorization_content, container, false);

        getActivity().setTitle("Messenger");
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        Button authorization = view.findViewById(R.id.send);

        model = ViewModelProviders.of(this).get(AuthorizationViewModel.class);

        editText = view.findViewById(R.id.login);
        editText1 = view.findViewById(R.id.password);

        model.getData().observe(this, new Observer<UserSecurity>() {

            @Override
            public void onChanged(UserSecurity userSecurity) {
                if(userSecurity!=null){
                    token = "Bearer_" + userSecurity.getToken();
                    authUser = userSecurity.getUsername();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    FragmentMainPage fragmentMainPage = new FragmentMainPage();
                    transaction.replace(R.id.main, fragmentMainPage);
                    transaction.commit();

                }
                else
                {
                    Toast toast = Toast.makeText(getActivity(), "Login or password incorrect",  Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


        authorization.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(isOnline()){
                    String login = editText.getText().toString();

                    String password = editText1.getText().toString();

                    UserAuthorization userAuthorization = new UserAuthorization(login, password);

                    model.setUserAuthorization(userAuthorization);
                    model.loadData();
                }
                else
                {
                    Toast toast = Toast.makeText(getActivity(), "No internet connection",  Toast.LENGTH_LONG);
                    toast.show();
                }

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


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
