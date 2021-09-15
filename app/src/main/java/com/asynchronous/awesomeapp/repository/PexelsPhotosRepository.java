package com.asynchronous.awesomeapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asynchronous.awesomeapp.R;
import com.asynchronous.awesomeapp.model.PexelsModel;
import com.asynchronous.awesomeapp.model.ResultModel;
import com.asynchronous.awesomeapp.ws.api.ApiPexelsInterface;
import com.asynchronous.awesomeapp.ws.client.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PexelsPhotosRepository extends BaseRepository {

    private static final String TAG = PexelsPhotosRepository.class.getSimpleName();

    private static volatile PexelsPhotosRepository instance;

    public static PexelsPhotosRepository getInstance() {
        if(instance == null) {
            instance = new PexelsPhotosRepository();
        }
        return instance;
    }

    public LiveData<ResultModel> getListPhotos(final Integer currentPage) {
        final MutableLiveData<ResultModel> liveData = new MutableLiveData<>();
        final ApiPexelsInterface apiPexelsInterface = ApiClient.getClient().create(ApiPexelsInterface.class);
        final Call<ResultModel> responseCall = apiPexelsInterface.getListPhotos(application.getString(R.string.token), 10, currentPage);
        responseCall.enqueue(new Callback<ResultModel>() {
            ResultModel resultModel = new ResultModel();
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        resultModel = response.body();
                        liveData.postValue(resultModel);
                    } else {
                        liveData.postValue(null);
                    }
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                liveData.postValue(null);
            }
        });

        return liveData;
    }


}
