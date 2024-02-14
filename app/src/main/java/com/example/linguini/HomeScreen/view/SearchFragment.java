package com.example.linguini.HomeScreen.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.linguini.R;
import com.google.android.material.search.SearchBar;

public class SearchFragment extends Fragment {

    private SearchBar searchBar;

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

    private void showSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchBar, InputMethodManager.SHOW_FORCED);
    }
}
