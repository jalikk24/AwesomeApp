package com.asynchronous.awesomeapp.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.asynchronous.awesomeapp.model.PexelsModel;
import com.asynchronous.awesomeapp.model.ResultModel;
import com.asynchronous.awesomeapp.repository.PexelsPhotosRepository;

public class PexelsPhotoViewModel extends BaseViewModel {

    private final PexelsPhotosRepository pexelsPhotosRepository;

    public PexelsPhotoViewModel(@NonNull Application application, PexelsPhotosRepository pexelsPhotosRepository) {
        super(application, pexelsPhotosRepository);
        this.pexelsPhotosRepository = pexelsPhotosRepository;
    }

    public LiveData<ResultModel> getListPhotos() {
        return this.pexelsPhotosRepository.getListPhotos();
    }
}
