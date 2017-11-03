package com.example.theodhor.retrofit2.Events;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kamal on 30/10/17.
 */

public class ApiTokenModel implements Serializable {

    @SerializedName("conversationId")
    private String conversationId;
    @SerializedName("token")
    private String token;
    @SerializedName("expires_in")
    private Integer expiresIn;
    private final static long serialVersionUID = -6404028755456677597L;

    @SerializedName("conversationId")
    public String getConversationId() {
        return conversationId;
    }

    @SerializedName("conversationId")
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @SerializedName("token")
    public String getToken() {
        return token;
    }

    @SerializedName("token")
    public void setToken(String token) {
        this.token = token;
    }

    @SerializedName("expires_in")
    public Integer getExpiresIn() {
        return expiresIn;
    }

    @SerializedName("expires_in")
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

}
