package com.asynchronous.awesomeapp.ws.api;

import com.asynchronous.awesomeapp.model.PexelsModel;
import com.asynchronous.awesomeapp.model.ResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiPexelsInterface {

    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })

    @GET("curated")
    Call<ResultModel> getListPhotos(@Header("Authorization") String token,
                                    @Query("per_page") Integer perPage,
                                    @Query("page") Integer page);

}
