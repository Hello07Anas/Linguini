package com.example.linguini.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.example.linguini.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the container view with the LoginFragment
        fragmentTransaction.replace(R.id.fragment_container, new RegisterationFregment());

        // Commit the transaction
        fragmentTransaction.commit();

    }
}