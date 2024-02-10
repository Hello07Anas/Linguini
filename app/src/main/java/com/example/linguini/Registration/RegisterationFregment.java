package com.example.linguini.Registration;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.linguini.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class RegisterationFregment extends Fragment {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    Button buttonSkip, buttonSignUp, buttonLogin;
    ImageView imageGoogle, imageX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_registeration_fregment, container, false);

        // Initialize your UI components using rootView.findViewById()
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions); // TODO maybe Bougs here

        buttonSkip = rootView.findViewById(R.id.btnSkip);
        buttonSignUp = rootView.findViewById(R.id.buttonSignUp);
        buttonLogin = rootView.findViewById(R.id.buttonLogin);
        imageGoogle = rootView.findViewById(R.id.imageGoogle);
        imageX = rootView.findViewById(R.id.imageX);

        // Set click listeners
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SignUpFragment
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, signUpFragment);
                transaction.addToBackStack(null);  // Optional: Add to back stack for back navigation
                transaction.commit();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SignUpFragment
                LoginFragment loginFragment = new LoginFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, loginFragment);
                transaction.addToBackStack(null);  // Optional: Add to back stack for back navigation
                transaction.commit();
            }
        });

        imageGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sginIn();
                Toast.makeText(getContext(), "Google", Toast.LENGTH_LONG).show();
                // TODO: Implement Google registration using Firebase
            }
        });



        imageX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "X", Toast.LENGTH_LONG).show();
                // TODO: Implement X registration using Firebase
            }
        });

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO  Navigate to the Home
                Toast.makeText(getContext(), "Skipped", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    void sginIn(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Toast.makeText(getContext(), "Congrats", Toast.LENGTH_LONG).show();
                // TODO navigate to Home
            } catch (ApiException e) {
                Toast.makeText(getContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
