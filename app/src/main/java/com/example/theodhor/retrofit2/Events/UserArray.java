package com.example.theodhor.retrofit2.Events;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserArray implements Serializable
{

    @SerializedName("id")
    private String id;
    private final static long serialVersionUID = -1629045471354451496L;

    @SerializedName("id")
    public String getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(String id) {
        this.id = id;
    }

}