package com.example.linguini.Registration;

import android.content.Context;
import android.content.Intent;
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

import com.example.linguini.HomeScreen.view.HomeActivity;
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

        View rootView = inflater.inflate(R.layout.fragment_registeration_fregment, container, false);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        Context context = getContext();
        if (context != null) {
            googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
        } else {
            Toast.makeText(getContext(), "we try to fix it sorry! \uD83D\uDE1E", Toast.LENGTH_LONG).show();
        }

        buttonSkip = rootView.findViewById(R.id.btnSkip);
        buttonSignUp = rootView.findViewById(R.id.buttonSignUp);
        buttonLogin = rootView.findViewById(R.id.buttonLogin);
        imageGoogle = rootView.findViewById(R.id.imageGoogle);
        imageX = rootView.findViewById(R.id.imageX);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment signUpFragment = new SignUpFragment();
                navigateToFragment(signUpFragment);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                navigateToFragment(loginFragment);
            }
        });

        imageGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
                Toast.makeText(getContext(), "u are trying to sgin in with google", Toast.LENGTH_LONG).show();
            }
        });
        imageX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Error! try again after 1 year \uD83D\uDE04", Toast.LENGTH_LONG).show();
                // TODO: Implement X registration using Firebase
            }
        });
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "welcome! \uD83D\uDC4B", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 9001);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(getContext(), "Congrats \uD83C\uDF89", Toast.LENGTH_LONG).show();
                // TODO: Navigate to Home
            } catch (ApiException e) {
                Toast.makeText(getContext(), "Something went Wrong: ⚠\uFE0F" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // TODO Bougs here: Add to back stack for back navigation
        transaction.commit();
    }
}