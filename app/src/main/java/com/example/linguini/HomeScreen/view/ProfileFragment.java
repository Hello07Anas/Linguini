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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.linguini.R;
import com.example.linguini.Registration.LoginFragment;
import com.example.linguini.Registration.RegistrationActivity;
import com.example.linguini.Registration.SignUpFragment;


public class ProfileFragment extends Fragment {

    Button btnLogOut;
    SharedPreferences preferences;
    LoginFragment loginFragment;
    ImageView gifOfProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getActivity().getSharedPreferences("AuthState", Context.MODE_PRIVATE);
        loginFragment = new LoginFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        gifOfProfile = requireView().findViewById(R.id.gifOfProfile);
        Glide.with(requireContext())
                .asGif()
                .load(R.drawable.pane_animation)
                .placeholder(R.drawable.palastine)
                .error(R.drawable.palastine)
                .into(gifOfProfile);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "We will miss you \uD83D\uDE22 \uD83D\uDC94", Toast.LENGTH_SHORT).show();
                clearAuthState();
                Intent intent = new Intent(getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // TODO Optional: Add to back stack for back navigation
        transaction.commit();
    }

    private void clearAuthState() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("IsLoggedIn", false);
        editor.apply();
    }
}