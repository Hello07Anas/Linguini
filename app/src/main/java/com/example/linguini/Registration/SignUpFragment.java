package com.example.linguini.Registration;

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

import com.example.linguini.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpFragment extends Fragment {

    FirebaseAuth mAuth;

    EditText edittxtName, edittxtEmail, edittxtPassword, edittxtRePassword;

    TextView txtLoginScreen;

    ImageView imagePassOn, imageRePassOn;

    Button btnSignUp;

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mAuth = FirebaseAuth.getInstance();

        edittxtName = rootView.findViewById(R.id.edittxtName);
        edittxtEmail = rootView.findViewById(R.id.edittxtEmail);
        edittxtPassword = rootView.findViewById(R.id.edittxtPassword);
        edittxtRePassword = rootView.findViewById(R.id.edittxtRePassword);
        btnSignUp = rootView.findViewById(R.id.btnSignUp);
        progressBar = rootView.findViewById(R.id.progressBar);
        txtLoginScreen = rootView.findViewById(R.id.txtLoginScreen);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = edittxtEmail.getText().toString();
                String password = edittxtPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Enter email", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Enter password", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getContext(), "Account created successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


        txtLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO <<< important >>>
                // Navigate to the SignUpFragment
                LoginFragment loginFragment = new LoginFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, loginFragment);
                transaction.addToBackStack(null);  // Optional: Add to back stack for back navigation
                transaction.commit();
            }
        });
        return rootView;
    }
}
