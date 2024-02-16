package com.example.linguini.HomeScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.linguini.R;


public class MyPlanFragment extends Fragment {


    private ImageView imgCommingSoon1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_plan, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgCommingSoon1 = requireView().findViewById(R.id.imgCommingSoon1);
        Glide.with(requireContext())
                .asGif() // Specify that the resource is a GIF
                .load(R.drawable.comming_soon) // Load the GIF from resources
                .placeholder(R.drawable.palastine) // Placeholder image while loading
                .error(R.drawable.palastine) // Error image if loading fails
                .into(imgCommingSoon1); // Set the GIF to the ImageView
    }
}