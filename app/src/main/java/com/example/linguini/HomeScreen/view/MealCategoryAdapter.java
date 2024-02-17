package com.example.linguini.HomeScreen.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealCategory;
import com.example.linguini.R;

import java.util.ArrayList;
import java.util.List;

public class MealCategoryAdapter extends RecyclerView.Adapter<MealCategoryAdapter.ViewHolder> {

    private static final String TAG = "MealCategoryAdapter";
    private List<PojoForMealCategory> mealCategories;

    public MealCategoryAdapter(List<PojoForMealCategory> mealCategories) {
        this.mealCategories = mealCategories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mealCategories != null && position < mealCategories.size()) {
            PojoForMealCategory mealCategory = mealCategories.get(position);

            // Set category name
            holder.categoryName.setText(mealCategory.getStrCategory());

            // Load category image using Glide
            Glide.with(holder.itemView)
                    .load(mealCategory.getStrCategoryThumb()) // Replace imageUrl with the actual URL of the image
                    .placeholder(R.drawable.palastine) // Placeholder image while loading
                    .into(holder.categoryImage);
        }
    }

    @Override
    public int getItemCount() {
        return mealCategories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryImage;

        ViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryImage = itemView.findViewById(R.id.category_image);
        }
    }

    public void updateData(List<PojoForMealCategory> newData) {
        if (newData != null) {
            Log.d(TAG, "Updating meal category adapter with new data, size: " + newData.size()); // Log the size of new data
            // Initialize the dataset if it's null
            if (mealCategories == null) {
                mealCategories = new ArrayList<>();
            }
            // Clear the existing data and add new data
            mealCategories.clear();
            mealCategories.addAll(newData);
            notifyDataSetChanged();
            Log.d(TAG, "Dataset updated and notifyDataSetChanged() called"); // Log when dataset is updated and notifyDataSetChanged() is called
        }
    }


}
