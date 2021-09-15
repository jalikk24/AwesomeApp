package com.asynchronous.awesomeapp.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.asynchronous.awesomeapp.repository.BaseRepository;

public class BaseViewModel extends AndroidViewModel {

    protected BaseRepository baseRepository;

    public BaseViewModel(@NonNull Application application, BaseRepository baseRepository) {
        super(application);
        this.baseRepository = baseRepository;
        this.baseRepository.setApplication(application);
    }

    public boolean isNetworkOnline() {
        return this.baseRepository.isNetworkOnline();
    }
}
