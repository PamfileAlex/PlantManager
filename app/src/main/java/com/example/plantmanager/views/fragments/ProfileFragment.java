package com.example.plantmanager.views.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.plantmanager.R;
import com.example.plantmanager.data_access.UserDataAccess;
import com.example.plantmanager.databinding.FragmentProfileBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.LoggedUserManager;

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

        User user = LoggedUserManager.INSTANCE.getLoggedUser();
        populateFields(user);

        binding.btnSave.setOnClickListener(view -> manageSave(user));

        binding.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoggedUserManager.INSTANCE.logout(getActivity());
            }
        });

        binding.tvDeleteAccount.setOnClickListener(view -> {
            showDeleteAccountDialog();
        });

        return binding.getRoot();
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

    private void showDeleteAccountDialog() {
        final CharSequence[] options = {"Delete", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.delete_account_dialog_message);
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Delete")) {
                LoggedUserManager.INSTANCE.getLoggedUser().setActive(false);
                UserDataAccess.updateUser(LoggedUserManager.INSTANCE.getLoggedUser());
                LoggedUserManager.INSTANCE.logout(getActivity());
            }
        });
        builder.show();
    }
}