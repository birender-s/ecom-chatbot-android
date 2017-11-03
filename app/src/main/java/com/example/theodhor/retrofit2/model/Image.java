
package com.example.theodhor.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable
{

    @SerializedName("url")
    private String url;
    @SerializedName("tap")
    private Tap tap;
    private final static long serialVersionUID = -1258849698162134711L;

    @SerializedName("url")
    public String getUrl() {
        return url;
    }

    @SerializedName("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("tap")
    public Tap getTap() {
        return tap;
    }

    @SerializedName("tap")
    public void setTap(Tap tap) {
        this.tap = tap;
    }

}
