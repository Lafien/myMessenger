package com.example.firstandroidapplication.users;


import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.R;

import java.util.List;

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

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterContacts.ViewHolder holder, final int position) {
        UserInfo contact = contacts.get(position);
        holder.imageView.setImageResource(R.drawable.ic_action_user);
        holder.surnameView.setText(contact.getSurname());
        holder.firstnameView.setText(contact.getFirstname());
        holder.usernameView.setText(contact.getUsername());

    }



    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView surnameView, firstnameView, usernameView;

        ViewHolder(final View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image);
            surnameView = (TextView) view.findViewById(R.id.surname);
            firstnameView = (TextView) view.findViewById(R.id.firstname);
            usernameView = (TextView) view.findViewById(R.id.username);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    chooseContactFromContacts = contacts.get(getAdapterPosition());

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();

                    Fragment myFragment = new FragmentUserContactsProfile();

                    activity.getFragmentManager().beginTransaction().replace(R.id.main, myFragment).addToBackStack(null).commit();
                }
            });
        }
    }


}
