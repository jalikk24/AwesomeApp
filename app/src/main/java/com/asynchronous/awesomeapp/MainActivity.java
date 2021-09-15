package com.asynchronous.awesomeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.asynchronous.awesomeapp.view.model.PexelsPhotoViewModel;
import com.asynchronous.awesomeapp.view.model.ViewModelFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private PexelsPhotoViewModel pexelsPhotoViewModel;
    private boolean isOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        pexelsPhotoViewModel = new ViewModelProvider(
                getViewModelStore(), new ViewModelFactory(getApplication())).
                get(PexelsPhotoViewModel.class);

        isOnline = pexelsPhotoViewModel.isNetworkOnline();

        pexelsPhotoViewModel.getListPhotos().observe(this, listPhotos -> {
            if (isOnline) {
                if (listPhotos != null) {
                    Log.i(TAG, listPhotos.getPhotos().toString());
                } else {
                    ToastMessage.showMessage(getBaseContext(), getBaseContext().getString(R.string.failedToGetData));
                }
            } else {
                ToastMessage.showMessage(getBaseContext(), getBaseContext().getString(R.string.offlineDevice));
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getBaseContext().getString(R.string.app_name));

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.white));

    }
}