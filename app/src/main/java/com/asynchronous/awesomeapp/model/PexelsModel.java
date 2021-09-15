package com.asynchronous.awesomeapp.model;

import com.google.gson.annotations.SerializedName;

public class PexelsModel {

    @SerializedName("src")
    private PexelsModel src;

    @SerializedName("original")
    private String original;

    @SerializedName("portrait")
    private String portrait;

    @SerializedName("landscape")
    private String landscape;

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
