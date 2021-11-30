package com.example.plantmanager.utils;

import com.example.plantmanager.models.User;

public enum CurrentUser {
    INSTANCE;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
