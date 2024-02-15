package com.example.linguini.HomeScreen.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.linguini.R;
import com.example.linguini.Registration.LoginFragment;
import com.example.linguini.Registration.RegistrationActivity;
import com.example.linguini.Registration.SignUpFragment;


public class ProfileFragment extends Fragment {

    Button btnLogOut;
    SharedPreferences preferences;
    LoginFragment loginFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getActivity().getSharedPreferences("AuthState", Context.MODE_PRIVATE);
        loginFragment = new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogOut = view.findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clearAuthState();
                Intent intent = new Intent(getContext(), RegistrationActivity.class);
                startActivity(intent);
                //navigateToFragment(loginFragment);
            }
        });
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // Optional: Add to back stack for back navigation
        transaction.commit();
    }

    private void clearAuthState() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("IsLoggedIn", false);
        editor.apply();
    }
}