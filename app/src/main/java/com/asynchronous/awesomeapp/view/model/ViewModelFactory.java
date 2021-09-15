package com.asynchronous.awesomeapp.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.asynchronous.awesomeapp.repository.PexelsPhotosRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ViewModelFactory(Application application) {
        super();
        this.application = application;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PexelsPhotoViewModel.class)) {
            return (T) new PexelsPhotoViewModel(this.application,
                    PexelsPhotosRepository.getInstance());
        } else {
            throw new IllegalArgumentException("Unknown View Model Class");
        }
    }
}
