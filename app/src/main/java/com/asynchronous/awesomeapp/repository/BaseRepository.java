package com.asynchronous.awesomeapp.repository;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BaseRepository {

    protected Application application;
    protected boolean isOnline = false;

    public final boolean isNetworkOnline() {
        if (this.application != null) {
            final ConnectivityManager cm = (ConnectivityManager) application.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    isOnline = networkInfo.isConnected();
                }

                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    isOnline = networkInfo.isConnected();
                }
            } else {
                isOnline = false;
            }
        }
        return this.isOnline;
    }

    public final void setApplication(final Application application) {
        this.application = application;
    }

}
