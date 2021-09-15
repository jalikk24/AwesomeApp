package com.asynchronous.awesomeapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.asynchronous.awesomeapp.Mode;
import com.asynchronous.awesomeapp.R;
import com.asynchronous.awesomeapp.ToastMessage;
import com.asynchronous.awesomeapp.adapter.AdapterPhotos;
import com.asynchronous.awesomeapp.model.PexelsModel;
import com.asynchronous.awesomeapp.model.ResultModel;
import com.asynchronous.awesomeapp.view.model.PexelsPhotoViewModel;
import com.asynchronous.awesomeapp.view.model.ViewModelFactory;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterPhotos.OnItemClickListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int SPAN_COUNT = 2;
    private PexelsPhotoViewModel pexelsPhotoViewModel;
    private AdapterPhotos adapterPhotos;
    private ShimmerFrameLayout shimmerFrameLayout;
    private List<PexelsModel> listPhoto = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;

    private boolean isOnline;
    private String mCurrentLayoutManagerType;
    private Integer currentPagePagin = 1;

    private RecyclerView recyclerPhotos;
    private NumberPicker numberPickPagination;
    private ImageButton btnDecreasePage;
    private ImageButton btnIncreasePage;
    private ImageButton imgList;
    private ImageButton imgGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        pexelsPhotoViewModel = new ViewModelProvider(
                getViewModelStore(), new ViewModelFactory(getApplication())).
                get(PexelsPhotoViewModel.class);

        isOnline = pexelsPhotoViewModel.isNetworkOnline();

        imgList = findViewById(R.id.imgList);
        imgGrid = findViewById(R.id.imgGrid);
        btnDecreasePage = findViewById(R.id.btnDecreasePage);
        btnIncreasePage = findViewById(R.id.btnIncreasePage);
        numberPickPagination = findViewById(R.id.numberPickPagination);
        recyclerPhotos = findViewById(R.id.recyclerPhotos);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);

        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mCurrentLayoutManagerType = Mode.LINEAR_LAYOUT_MANAGER;

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getBaseContext().getString(R.string.app_name));

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.white));

        imgList.setOnClickListener(this);
        imgGrid.setOnClickListener(this);
        btnDecreasePage.setOnClickListener(this);
        btnIncreasePage.setOnClickListener(this);

        numberPickPagination.setFadingEdgeEnabled(true);
        numberPickPagination.setScrollerEnabled(false);
        numberPickPagination.setWrapSelectorWheel(true);

        getListPhotos();

    }

    private void getListPhotos() {
        shimmerFrameLayout.startShimmerAnimation();
        pexelsPhotoViewModel.getListPhotos(currentPagePagin).observe(this, listPhotos -> {
            if (isOnline) {
                if (listPhotos != null) {
                    recyclerPhotos.setHasFixedSize(true);
                    setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
                    adapterPhotos = new AdapterPhotos(getBaseContext(), listPhotos.getPhotos(), mCurrentLayoutManagerType);
                    adapterPhotos.setOnItemClickListener(this);
                    Log.i(TAG, listPhotos.getPhotos().toString());
                    shimmerFrameLayout.stopShimmerAnimation();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerPhotos.setVisibility(View.VISIBLE);
                    recyclerPhotos.setAdapter(adapterPhotos);
                    recyclerPhotos.setNestedScrollingEnabled(true);
                    listPhoto = listPhotos.getPhotos();
                    pagination(listPhotos);
                } else {
                    ToastMessage.showMessage(getBaseContext(), getBaseContext().getString(R.string.failedToGetData));
                    shimmerFrameLayout.stopShimmerAnimation();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }
            } else {
                ToastMessage.showMessage(getBaseContext(), getBaseContext().getString(R.string.offlineDevice));
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        });
    }

    public void setRecyclerViewLayoutManager(final String layoutType) {
        int scrollPosition = 0;
        if (recyclerPhotos.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerPhotos.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        if (Mode.GRID_LAYOUT_MANAGER.equals(layoutType)) {
            mLayoutManager = new GridLayoutManager(getBaseContext(), SPAN_COUNT);
            mCurrentLayoutManagerType = Mode.GRID_LAYOUT_MANAGER;
        } else {
            mLayoutManager = new LinearLayoutManager(getBaseContext());
            mCurrentLayoutManagerType = Mode.LINEAR_LAYOUT_MANAGER;
        }
        recyclerPhotos.setLayoutManager(mLayoutManager);
        recyclerPhotos.scrollToPosition(scrollPosition);
        adapterPhotos = new AdapterPhotos(getBaseContext(), listPhoto, mCurrentLayoutManagerType);
        adapterPhotos.setOnItemClickListener(this);
        recyclerPhotos.setAdapter(adapterPhotos);
    }

    private void pagination(final ResultModel resultModel) {
        final Integer totalPageCount = resultModel.getTotal_results();
        int modulusTotalPageCount = totalPageCount % 10;
        int totalPageResult;

        if (modulusTotalPageCount != 0) {
            totalPageResult = (totalPageCount - modulusTotalPageCount) + 10;
            totalPageResult = totalPageResult / 10;
        } else {
            totalPageResult = totalPageCount / 10;
        }
        numberPickPagination.setMaxValue(totalPageResult);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getBaseContext(), DetailPhotosActivity.class);
        PexelsModel pexelsModel = listPhoto.get(position);
        bundle.putSerializable("modelData", pexelsModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == imgList) {
            setRecyclerViewLayoutManager(Mode.LINEAR_LAYOUT_MANAGER);
        }
        if (view == imgGrid) {
            setRecyclerViewLayoutManager(Mode.GRID_LAYOUT_MANAGER);
        }
        if (view == btnIncreasePage) {
            int currentPage = numberPickPagination.getValue();
            int maxValue = numberPickPagination.getMaxValue();
            if (currentPage == maxValue) {
                numberPickPagination.setValue(numberPickPagination.getMinValue());
                currentPagePagin = numberPickPagination.getValue();
                getListPhotos();
            } else {
                numberPickPagination.setValue(currentPage + 1);
                currentPagePagin = numberPickPagination.getValue();
                getListPhotos();
            }
        }
        if (view == btnDecreasePage) {
            int currentPage = numberPickPagination.getValue();
            if (currentPage == numberPickPagination.getMinValue()) {
                numberPickPagination.setValue(numberPickPagination.getMaxValue());
                currentPagePagin = numberPickPagination.getValue();
                getListPhotos();
            } else {
                numberPickPagination.setValue(currentPage - 1);
                currentPagePagin = numberPickPagination.getValue();
                getListPhotos();
            }
        }
    }
}