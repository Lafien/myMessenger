package com.example.firstandroidapplication.users;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.authorization.FragmentUserAuthorization;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.MainActivity.actionBar;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.authUser;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class FragmentUserMyProfile extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.my_profile_content, container, false);

        getActivity().setTitle("My profile");


        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        final TextView textView =  view.findViewById(R.id.username);
        final TextView textView1 =  view.findViewById(R.id.surname);
        final TextView textView2 =  view.findViewById(R.id.firstname);
        final CircleImageView image = view.findViewById(R.id.images);

        ConfigRetrofit.getInstance()
                .myProfile(token)
                .enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                        if(response.isSuccessful()) {
                            UserInfo post = response.body();

                            textView.setText(post.getUsername());
                            textView1.setText(post.getSurname());
                            textView2.setText(post.getFirstname());
                        }
                        else {
                            Toast toast = Toast.makeText(getActivity(), "JWT token is expired or invalid",  Toast.LENGTH_LONG);
                            toast.show();

                            AppCompatActivity activity = (AppCompatActivity) view.getContext();

                            FragmentUserAuthorization fragmentUserAuthorization = new FragmentUserAuthorization();

                            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentUserAuthorization);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        Toast toast = Toast.makeText(getActivity(), "No response from server",  Toast.LENGTH_LONG);
                        toast.show();
                        t.printStackTrace();
                    }
                });


        ConfigRetrofit.getInstance()
                .getImage(token, authUser)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.isSuccessful()) {

                            assert response.body() != null;

                            InputStream  bytes = response.body().byteStream();
                            Bitmap bitmap = null;
                            bitmap = BitmapFactory.decodeStream(bytes);

                            image.setImageBitmap(bitmap);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
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
