
package com.example.theodhor.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attachment implements Serializable
{

    @SerializedName("contentType")
    private String contentType;
    @SerializedName("content")
    private Content content;
    private final static long serialVersionUID = -6245312675005886441L;

    @SerializedName("contentType")
    public String getContentType() {
        return contentType;
    }

    @SerializedName("contentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @SerializedName("content")
    public Content getContent() {
        return content;
    }

    @SerializedName("content")
    public void setContent(Content content) {
        this.content = content;
    }

}
