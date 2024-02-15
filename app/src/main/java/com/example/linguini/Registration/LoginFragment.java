package com.example.linguini.Registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linguini.HomeScreen.view.HomeActivity;
import com.example.linguini.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {


    private static final String PREF_NAME = "AuthState";
    private static final String IS_LOGGED_IN = "IsLoggedIn";
    SharedPreferences preferences;

    FirebaseAuth mAuth;
    EditText editTextEmailAddress, editTextPassword;
    Button btnLogin;
    ImageView imagePasswordOn;
    ProgressBar progressBar;
    TextView txtSignUp;  // TODO <<< here will take me to Sign up fragment >>>

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize your UI components here using rootView.findViewById()
        editTextEmailAddress = rootView.findViewById(R.id.editTextEmailAddress);
        editTextPassword = rootView.findViewById(R.id.editTextPassword);
        btnLogin = rootView.findViewById(R.id.btnLogin);
        txtSignUp = rootView.findViewById(R.id.txtSignUp);
        progressBar = rootView.findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        preferences = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean(IS_LOGGED_IN, false);

        if (isLoggedIn) {
            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
        }

        // Set click listener for the sign-up TextView
        // Set click listener for the sign-up TextView

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
                // TODO un comment the code its very important I just comment to can move faster to Home
                progressBar.setVisibility(View.VISIBLE);
                String email = editTextEmailAddress.getText().toString();
                String password = editTextPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "enter email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getContext(), "welcome to Linguini", Toast.LENGTH_LONG).show();
                                    changeState();

                                    Intent intent = new Intent(getContext(), HomeActivity.class);
                                    startActivity(intent);
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getContext(), "the password or email incorrect :(", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                progressBar.setVisibility(View.GONE);
            }
        });


        txtSignUp.setOnClickListener(new View.OnClickListener() {
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
        return rootView;
    }

    private void changeState() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.apply();
    }

}
