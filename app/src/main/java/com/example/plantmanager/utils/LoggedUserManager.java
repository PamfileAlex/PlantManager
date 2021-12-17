package com.example.plantmanager.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.activities.LoginActivity;
import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.models.User;

public enum LoggedUserManager {
    INSTANCE;
    private User user;

    public User getLoggedUser() {
        return user;
    }

    public void login(User user) {
        this.user = user;
    }

    public void logout(Activity activity) {
        if (user == null) {
            return;
        }
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        user = null;
        activity.startActivity(intent);
    }
}
