package com.example.theodhor.retrofit2.Events;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConversationModel implements Serializable
{

    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private String id;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("localTimestamp")
    private String localTimestamp;
    @SerializedName("channelId")
    private String channelId;
    @SerializedName("from")
    private From from;
    @SerializedName("conversation")
    private Conversation conversation;
    @SerializedName("text")
    private String text;
    @SerializedName("inputHint")
    private String inputHint;
    @SerializedName("replyToId")
    private String replyToId;
    private final static long serialVersionUID = 8620088380643646410L;

    @SerializedName("type")
    public String getType() {
        return type;
    }

    @SerializedName("type")
    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("id")
    public String getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @SerializedName("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @SerializedName("localTimestamp")
    public String getLocalTimestamp() {
        return localTimestamp;
    }

    @SerializedName("localTimestamp")
    public void setLocalTimestamp(String localTimestamp) {
        this.localTimestamp = localTimestamp;
    }

    @SerializedName("channelId")
    public String getChannelId() {
        return channelId;
    }

    @SerializedName("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @SerializedName("from")
    public From getFrom() {
        return from;
    }

    @SerializedName("from")
    public void setFrom(From from) {
        this.from = from;
    }

    @SerializedName("conversation")
    public Conversation getConversation() {
        return conversation;
    }

    @SerializedName("conversation")
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @SerializedName("text")
    public String getText() {
        return text;
    }

    @SerializedName("text")
    public void setText(String text) {
        this.text = text;
    }

    @SerializedName("inputHint")
    public String getInputHint() {
        return inputHint;
    }

    @SerializedName("inputHint")
    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    @SerializedName("replyToId")
    public String getReplyToId() {
        return replyToId;
    }

    @SerializedName("replyToId")
    public void setReplyToId(String replyToId) {
        this.replyToId = replyToId;
    }

}
