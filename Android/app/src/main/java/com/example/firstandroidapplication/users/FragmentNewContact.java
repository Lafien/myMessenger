package com.example.firstandroidapplication.users;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;

import static com.example.firstandroidapplication.MainActivity.actionBar;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNewContact extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_contact_content, container, false);

        getActivity().setTitle("New contact");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button addContact = view.findViewById(R.id.addContact);


        final EditText editText = view.findViewById(R.id.newContact);
        final Activity activity = getActivity();;


        addContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {

                final UserInfo userInfo = new UserInfo();

                String login = editText.getText().toString();

                userInfo.setUsername(login);


                ConfigRetrofit.getInstance()
                        .addContact(token, userInfo)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if (response.isSuccessful()) {

                                    FragmentTransaction fTrans;

                                    FragmentContacts fragmentContacts = new FragmentContacts();

                                    fTrans = getFragmentManager().beginTransaction();
                                    fTrans.replace(R.id.main, fragmentContacts);
                                    fTrans.addToBackStack(null);
                                    fTrans.commit();



                                }
                                else
                                    {

                                        Toast toast = Toast.makeText(activity, Html.fromHtml("<span style=\"background-color:#0099cb;" +
                                                " color:#ffffff\">This user does not exist</span>"),  Toast.LENGTH_LONG);

                                        LinearLayout toastContainer = (LinearLayout) toast.getView();
                                        toastContainer.setBackgroundResource(R.drawable.toast_style);

                                        toast.show();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
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
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

}
