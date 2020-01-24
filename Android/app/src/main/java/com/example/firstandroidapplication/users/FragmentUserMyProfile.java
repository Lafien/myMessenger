package com.example.firstandroidapplication.users;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import com.example.firstandroidapplication.R;
import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.authUser;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class FragmentUserMyProfile extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_profile_content, container, false);

        final TextView textView =  view.findViewById(R.id.username);
        final TextView textView1 =  view.findViewById(R.id.surname);
        final TextView textView2 =  view.findViewById(R.id.firstname);
        final TextView problem = view.findViewById(R.id.problem);
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
                            textView.setText("");
                            textView1.setText("");
                            textView2.setText("");
                            problem.setText("Проблемы с авторизацией");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {

                        textView.setText("Error occurred while getting request!");
                        textView1.setText("");
                        textView2.setText("");
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

        return  view;
    }




}
