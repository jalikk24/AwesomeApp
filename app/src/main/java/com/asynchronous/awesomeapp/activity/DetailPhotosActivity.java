package com.asynchronous.awesomeapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.asynchronous.awesomeapp.R;
import com.asynchronous.awesomeapp.model.PexelsModel;
import com.bumptech.glide.Glide;

public class DetailPhotosActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_photos);
        Toolbar toolbarDetail = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView txtDetailToolbar = findViewById(R.id.txtDetailToolbar);
        ImageView imgDetail = findViewById(R.id.imgDetail);
        TextView txtPhotographId = findViewById(R.id.txtPhotographId);
        TextView txtPhotographNameDetail = findViewById(R.id.txtPhotographNameDetail);

        PexelsModel pexelsModel = (PexelsModel) getIntent().getExtras().get("modelData");
        Log.i("test retreive", pexelsModel.getPhotographer());

        Glide.with(getBaseContext())
                .load(pexelsModel.getSrc().getLandscape())
                .centerCrop()
                .into(imgDetail);

        txtPhotographId.setText(getBaseContext().getString(R.string.photoId) + pexelsModel.getId());
        txtPhotographNameDetail.setText(pexelsModel.getPhotographer());
        txtDetailToolbar.setText(getBaseContext().getString(R.string.detail) + " " + pexelsModel.getPhotographer());

    }
}