package com.example.firstandroidapplication.authorization;


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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
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
    TextView textView;

    AuthorizationViewModel model;
    LiveData<UserSecurity> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.authorization_content, container, false);



        getActivity().setTitle("Messenger");
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        Button authorization = view.findViewById(R.id.send);

        model = ViewModelProviders.of(this).get(AuthorizationViewModel.class);

        editText = view.findViewById(R.id.login);
        editText1 = view.findViewById(R.id.password);
        textView = view.findViewById(R.id.textView2);


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
                    textView.setText("Login or password incorrect");
                }


            }
        });


        authorization.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String login = editText.getText().toString();

                String password = editText1.getText().toString();

                UserAuthorization userAuthorization = new UserAuthorization(login, password);

                model.setUserAuthorization(userAuthorization);
                model.loadData();

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
