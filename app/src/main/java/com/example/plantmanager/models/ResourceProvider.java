package com.example.plantmanager.models;

import android.content.Context;

public class ResourceProvider {
    private Context context;

    public ResourceProvider(Context context) {
        this.context = context;
    }

    public String getString(int resId) {
        return context.getString(resId);
    }

    public String getString(int resId, String value) {
        return context.getString(resId, value);
    }
}
