package com.example.plantmanager.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.activities.ApplicationActivity;
import com.example.plantmanager.activities.LoginActivity;
import com.example.plantmanager.activities.RegisterActivity;
import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.databinding.FragmentProfileBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.CurrentUser;
import com.example.plantmanager.view_models.ApplicationViewModel;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        User user = CurrentUser.INSTANCE.getUser();
        populateFields(user);

        binding.btnSave.setOnClickListener(view -> manageSave(user));

        binding.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        binding.tvDeleteAccount.setOnClickListener(view -> {
            CurrentUser.INSTANCE.getUser().setActive(false);
            UserDataAccess.updateUser(CurrentUser.INSTANCE.getUser());
            logout();
        });

        return binding.getRoot();
    }

    private void logout() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        CurrentUser.INSTANCE.setUser(null);
        startActivity(intent);
    }

    private void populateFields(User user) {
        binding.etProfileLastName.setText(user.getLastName());
        binding.etProfileFirstName.setText(user.getFirstName());
        binding.etProfileEmail.setText(user.getEmail());
        binding.etProfileUsername.setText(user.getUsername());
    }

    private void manageSave(User user) {
        String lastName = binding.etProfileLastName.getText().toString();
        String firstName = binding.etProfileFirstName.getText().toString();
        String email = binding.etProfileEmail.getText().toString();

        if (lastName.equals("") || firstName.equals("") || email.equals("")) {
            Toast.makeText(getContext(), R.string.incomplete_form, Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(lastName, firstName, email, user.getUsername(), user.getPassword(), user.isActive());
        UserDataAccess.updateUser(newUser);
        Toast.makeText(getContext(), R.string.profile_save, Toast.LENGTH_SHORT).show();
    }
}