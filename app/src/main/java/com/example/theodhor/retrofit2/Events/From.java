package com.example.theodhor.retrofit2.Events;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class From implements Serializable
{

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    private final static long serialVersionUID = -3892888225093294961L;

    @SerializedName("id")
    public String getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("name")
    public String getName() {
        return name;
    }

    @SerializedName("name")
    public void setName(String name) {
        this.name = name;
    }

}