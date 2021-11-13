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

    Connection connection;
    String connectionResult = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.tvLoginRegister.setOnClickListener(view -> NavHostFragment.findNavController(LoginFragment.this)
                .navigate(R.id.navigate_from_loginFragment_to_registerFragment));

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                test();
            }
        });

        return binding.getRoot();
    }

    public void test(){
        try{
            SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
            sqlConnectionManager.getSqlConnectionManager();
//            connection = sqlConnectionManager.getSqlConnectionManager();
//            if(connection!=null) {
//                String q = "Select * from User";
//                Statement st = connection.createStatement();
//                ResultSet res = st.executeQuery(q);
//
//                while (res.next()){
//                    System.out.println(res.getString(1));
//                }
//            }
//            else
//            {
//                connectionResult="Check conn";
//            }
//            System.out.println();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}