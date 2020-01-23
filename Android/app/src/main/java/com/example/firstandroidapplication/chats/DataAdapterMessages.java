package com.example.firstandroidapplication.chats;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapplication.R;

import java.util.List;
import static com.example.firstandroidapplication.authorization.FragmentUserAuthorization.authUser;

public class DataAdapterMessages extends RecyclerView.Adapter<DataAdapterMessages.ViewHolder>  {

    private LayoutInflater inflater;
    private List<Message> messagesList;

    public void setMessagesList(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public DataAdapterMessages(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public DataAdapterMessages(Context context, List<Message> messagesList) {
        this.messagesList = messagesList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public DataAdapterMessages.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.messages_item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(DataAdapterMessages.ViewHolder holder, final int position) {

        Message message = messagesList.get(position);
        //holder.circleImageView.setImageResource(R.drawable.ic_action_user);


        String msgFrom = message.getMsgFrom();
        String msgTo = message.getMsgTo();

        if(msgFrom.equals(authUser)){

            holder.messageText.setBackgroundResource(R.drawable.message_text_background_two);

            holder.messageText.setTextColor(Color.BLACK);

            holder.messageText.setGravity(Gravity.RIGHT);

            holder.mesDate.setBackgroundResource(R.drawable.message_text_background_two);
            holder.mesDate.setTextColor(Color.BLACK);
            holder.mesDate.setGravity(Gravity.RIGHT);
            holder.mesDate.setPadding(0,0,20,0);


        }
        else
        {


            holder.messageText.setBackgroundResource(R.drawable.message_text_background);

            holder.messageText.setTextColor(Color.BLACK);

            holder.messageText.setGravity(Gravity.LEFT);

            holder.mesDate.setBackgroundResource(R.drawable.message_text_background);
            holder.mesDate.setTextColor(Color.BLACK);
            holder.mesDate.setGravity(Gravity.LEFT);
            holder.mesDate.setPadding(20,0,0,0);


        }

        holder.messageText.setText(message.getText());
        holder.mesDate.setText(message.getDateCreate());

    }



    @Override
    public int getItemCount() {
        return messagesList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        //public CircleImageView circleImageView;
        public TextView messageText;
        public TextView mesDate;

        ViewHolder(final View view){
            super(view);
            //circleImageView = view.findViewById(R.id.messageImage);
            messageText =  view.findViewById(R.id.messageText);
            mesDate = view.findViewById(R.id.mesDate);
        }
    }


}
