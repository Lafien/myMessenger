package com.example.firstandroidapplication.users;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.API.ConfigRetrofit;
import com.example.firstandroidapplication.R;

import java.io.InputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.token;

public class DataAdapterContacts extends RecyclerView.Adapter<DataAdapterContacts.ViewHolder>  {

    private LayoutInflater inflater;
    private List<UserInfo> contacts;

    public static UserInfo chooseContactFromContacts;


    public DataAdapterContacts(Context context, List<UserInfo> contacts) {
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public DataAdapterContacts.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.contacts_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapterContacts.ViewHolder holder, final int position) {
        UserInfo contact = contacts.get(position);
        //holder.imageView.setImageResource(R.drawable.pic1);
        holder.surnameView.setText(contact.getSurname());
        holder.firstnameView.setText(contact.getFirstname());
        holder.usernameView.setText(contact.getUsername());

        ConfigRetrofit.getInstance()
                .getImage(token, contact.getUsername())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.isSuccessful()) {

                            assert response.body() != null;

                            InputStream bytes = response.body().byteStream();
                            Bitmap bitmap = null;
                            bitmap = BitmapFactory.decodeStream(bytes);

                            holder.imageView.setImageBitmap(bitmap);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }



    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final CircleImageView imageView;
        final TextView surnameView, firstnameView, usernameView;

        ViewHolder(final View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            surnameView = (TextView) view.findViewById(R.id.surname);
            firstnameView = (TextView) view.findViewById(R.id.firstname);
            usernameView = (TextView) view.findViewById(R.id.username);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    chooseContactFromContacts = contacts.get(getAdapterPosition());

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();

                    FragmentUserContactsProfile fragmentUserContactsProfile = new FragmentUserContactsProfile();

                    FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentUserContactsProfile);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    //activity.getFragmentManager().beginTransaction().replace(R.id.main, myFragment).addToBackStack(null).commit();
                }
            });
        }
    }


}
