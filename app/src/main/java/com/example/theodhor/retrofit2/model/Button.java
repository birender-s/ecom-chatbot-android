
package com.example.theodhor.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Button implements Serializable
{

    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("value")
    private String value;
    private final static long serialVersionUID = -3045312772629558834L;

    @SerializedName("type")
    public String getType() {
        return type;
    }

    @SerializedName("type")
    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("title")
    public String getTitle() {
        return title;
    }

    @SerializedName("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("value")
    public String getValue() {
        return value;
    }

    @SerializedName("value")
    public void setValue(String value) {
        this.value = value;
    }

}
