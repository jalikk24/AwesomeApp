package com.asynchronous.awesomeapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PexelsModel implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("photographer")
    private String photographer;

    @SerializedName("src")
    private PexelsModel src;

    @SerializedName("original")
    private String original;

    @SerializedName("portrait")
    private String portrait;

    @SerializedName("landscape")
    private String landscape;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public PexelsModel getSrc() {
        return src;
    }

    public void setSrc(PexelsModel src) {
        this.src = src;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }
}
