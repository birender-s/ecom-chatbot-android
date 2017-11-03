
package com.example.theodhor.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Conversation implements Serializable
{

    @SerializedName("id")
    private String id;
    private final static long serialVersionUID = 7821964957034131470L;

    @SerializedName("id")
    public String getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(String id) {
        this.id = id;
    }

}
