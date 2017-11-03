
package com.example.theodhor.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ActivityModel implements Serializable
{

    @SerializedName("activities")
    private List<Activity> activities = null;
    @SerializedName("watermark")
    private String watermark;
    private final static long serialVersionUID = 7605006584631511693L;

    @SerializedName("activities")
    public List<Activity> getActivities() {
        return activities;
    }

    @SerializedName("activities")
    public void setActivities(List<Activity> activities) {
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
