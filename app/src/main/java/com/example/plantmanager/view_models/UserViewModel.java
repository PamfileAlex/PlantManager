package com.example.plantmanager.view_models;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.models.User;

public class UserViewModel extends ViewModel {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
