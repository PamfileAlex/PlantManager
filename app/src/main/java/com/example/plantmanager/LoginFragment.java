package com.example.plantmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantmanager.databinding.FragmentLoginBinding;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    SqlConnectionManager sqlConnectionManager;
    Connection databaseConnection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.tvLoginRegister.setOnClickListener(view -> NavHostFragment.findNavController(LoginFragment.this)
                .navigate(R.id.navigate_from_loginFragment_to_registerFragment));

//        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                connectToDataBase();
//            }
//        });

        return binding.getRoot();
    }

//    public void connectToDataBase() {
//        sqlConnectionManager = new SqlConnectionManager();
//
//        String classes = this.getString(R.string.db_classes);
//        String url = this.getString(R.string.db_connection_url);
//
//        try {
//            databaseConnection = sqlConnectionManager.getSqlConnection(classes, url);
//            sqlConnectionManager.printUsers(databaseConnection);
//            databaseConnection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}