package com.example.linguini.HomeScreen.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.linguini.R;
import com.google.android.material.search.SearchBar;

import java.util.Objects;

public class SearchFragment extends Fragment {

    private SearchBar searchBar;
    private ImageView imgCommingSoon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        searchBar = rootView.findViewById(R.id.search_bar);

        // Enable input and focus for the search bar
        searchBar.setFocusable(true);
        searchBar.setFocusableInTouchMode(true);

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.requestFocus();
                showSoftKeyboard();
            }
        });

        // Request focus for the search bar
        searchBar.requestFocus();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgCommingSoon = requireView().findViewById(R.id.imgCommingSoon);
        Glide.with(requireContext())
                .asGif() // Specify that the resource is a GIF
                .load(R.drawable.comming_soon) // Load the GIF from resources
                .placeholder(R.drawable.palastine) // Placeholder image while loading
                .error(R.drawable.palastine) // Error image if loading fails
                .into(imgCommingSoon); // Set the GIF to the ImageView
    }


    private void showSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchBar, InputMethodManager.SHOW_FORCED);
    }
}
