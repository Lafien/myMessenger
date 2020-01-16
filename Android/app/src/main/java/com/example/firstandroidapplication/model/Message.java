package com.example.firstandroidapplication.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("dateCreate")
    private String dateCreate;

    @SerializedName("text")
    private String text;

    @SerializedName("msgFrom")
    private String msgFrom;

    @SerializedName("msgTo")
    private String msgTo;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public Message(String dateCreate, String text, String msgFrom, String msgTo) {
        this.dateCreate = dateCreate;
        this.text = text;
        this.msgFrom = msgFrom;
        this.msgTo = msgTo;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

    public String getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo;
    }

    @Override
    public String toString() {
        return text + "  (" + dateCreate + ")";
    }
}
