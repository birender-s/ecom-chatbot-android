package com.example.theodhor.retrofit2.Events;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kamal on 01/11/17.
 */

public class ActivtiyModel implements Serializable {

    @SerializedName("activities")
    private List<ConversationModel> activities = null;
    @SerializedName("watermark")
    private String watermark;
    private final static long serialVersionUID = 9212427649740330857L;

    @SerializedName("activities")
    public List<ConversationModel> getActivities() {
        return activities;
    }

    @SerializedName("activities")
    public void setActivities(List<ConversationModel> activities) {
        this.activities = activities;
    }

    @SerializedName("watermark")
    public String getWatermark() {
        return watermark;
    }

    @SerializedName("watermark")
    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String type;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String userInput;
}