package com.example.theodhor.retrofit2.Events;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SendRequest implements Serializable {

    @SerializedName("type")
    private String type;
    @SerializedName("from")
    private UserArray from;
    @SerializedName("text")
    private String text;
    @SerializedName("locale")
    private String locale;
    @SerializedName("id")
    private String id;
    @SerializedName("channelId")
    private String channelId;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("localTimestamp")
    private String localTimestamp;
    @SerializedName("recipient")
    private Recipient recipient;
    @SerializedName("conversation")
    private ConversationRequest conversationRequest;
    @SerializedName("serviceUrl")
    private String serviceUrl;
    private final static long serialVersionUID = -5845931109237875829L;

    @SerializedName("type")
    public String getType() {
        return type;
    }

    @SerializedName("type")
    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("from")
    public UserArray getFrom() {
        return from;
    }

    @SerializedName("from")
    public void setFrom(UserArray from) {
        this.from = from;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocalTimestamp() {
        return localTimestamp;
    }

    public void setLocalTimestamp(String localTimestamp) {
        this.localTimestamp = localTimestamp;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public ConversationRequest getConversationRequest() {
        return conversationRequest;
    }

    public void setConversationRequest(ConversationRequest conversationRequest) {
        this.conversationRequest = conversationRequest;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @SerializedName("text")
    public String getText() {
        return text;
    }

    @SerializedName("text")
    public void setText(String text) {
        this.text = text;
    }

}
