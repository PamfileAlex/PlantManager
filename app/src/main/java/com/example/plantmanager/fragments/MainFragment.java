//package com.example.plantmanager.fragments;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.navigation.fragment.NavHostFragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.plantmanager.R;
//import com.example.plantmanager.databinding.FragmentMainBinding;
//import com.example.plantmanager.fragments.navigation_bar.HomeFragment;
//import com.example.plantmanager.view_models.MainViewModel;
//import com.google.android.material.navigation.NavigationBarView;
//
//
//public class MainFragment extends Fragment {
//    private FragmentMainBinding binding;
//    private MainViewModel viewModel;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        viewModel.setSelectedNavigationBarFragment(R.id.home);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        binding = FragmentMainBinding.inflate(inflater, container, false);
//        binding.bottomNavigation.setOnItemSelectedListener(navSelectedListener);
//
//        return binding.getRoot();
//    }
//
//    private final NavigationBarView.OnItemSelectedListener navSelectedListener = item -> {
//        switch (item.getItemId()) {
//            case R.id.home:
//                if (viewModel.getSelectedNavigationBarFragment() == R.id.home) {
//                    break;
//                }
//                //NavHostFragment.findNavController(FragmentManager.findFragment(new HomeFragment())).navigate(viewModel.getNavigation(R.id.home));
//                viewModel.setSelectedNavigationBarFragment(R.id.home);
//                break;
//            case R.id.add_plant:
//                if (viewModel.getSelectedNavigationBarFragment() == R.id.add_plant) {
//                    break;
//                }
//                NavHostFragment.findNavController(new HomeFragment()).navigate(viewModel.getNavigation(R.id.add_plant));
//                viewModel.setSelectedNavigationBarFragment(R.id.add_plant);
//                break;
//            case R.id.profile:
//                //NavHostFragment.findNavController(MainFragment.this).navigate(R.id.homeFragment);
//                break;
//            default:
//                break;
//        }
//        return true; // return true;
//    };
//}