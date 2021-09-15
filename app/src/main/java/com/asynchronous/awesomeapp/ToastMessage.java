package com.asynchronous.awesomeapp;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class ToastMessage {

    public static void showMessage(final Context context, final String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
