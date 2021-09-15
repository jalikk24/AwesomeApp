package com.asynchronous.awesomeapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModel {

    @SerializedName("page")
    private Integer page;

    @SerializedName("per_page")
    private Integer per_page;

    @SerializedName("photos")
    private List<PexelsModel> photos = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public List<PexelsModel> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PexelsModel> photos) {
        this.photos = photos;
    }
}
