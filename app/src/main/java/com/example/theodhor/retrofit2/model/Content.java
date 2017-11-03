
package com.example.theodhor.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Content implements Serializable
{

    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("text")
    private String text;
    @SerializedName("images")
    private List<Image> images = null;
    @SerializedName("buttons")
    private List<Button> buttons = null;
    private final static long serialVersionUID = 4478617744386532890L;

    @SerializedName("title")
    public String getTitle() {
        return title;
    }

    @SerializedName("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    @SerializedName("subtitle")
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @SerializedName("text")
    public String getText() {
        return text;
    }

    @SerializedName("text")
    public void setText(String text) {
        this.text = text;
    }

    @SerializedName("images")
    public List<Image> getImages() {
        return images;
    }

    @SerializedName("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @SerializedName("buttons")
    public List<Button> getButtons() {
        return buttons;
    }

    @SerializedName("buttons")
    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

}
