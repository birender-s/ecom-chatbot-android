package com.example.theodhor.retrofit2.Events;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class StartConversationModel implements Serializable
{

    @SerializedName("conversationId")
    private String conversationId;
    @SerializedName("token")
    private String token;
    @SerializedName("expires_in")
    private Integer expiresIn;
    @SerializedName("streamUrl")
    private String streamUrl;
    @SerializedName("referenceGrammarId")
    private String referenceGrammarId;
    private final static long serialVersionUID = -8081437597330671914L;

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

    @SerializedName("streamUrl")
    public String getStreamUrl() {
        return streamUrl;
    }

    @SerializedName("streamUrl")
    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    @SerializedName("referenceGrammarId")
    public String getReferenceGrammarId() {
        return referenceGrammarId;
    }

    @SerializedName("referenceGrammarId")
    public void setReferenceGrammarId(String referenceGrammarId) {
        this.referenceGrammarId = referenceGrammarId;
    }

}
