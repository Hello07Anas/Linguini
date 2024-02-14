package com.example.linguini.HomeScreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.R;
import java.util.List;

public class MealOfDayAdapter extends RecyclerView.Adapter<MealOfDayAdapter.MealViewHolder> {

    private List<PojoForMeal> meals;
    private Context context;

    public MealOfDayAdapter(List<PojoForMeal> meals) {
        this.meals = meals;
    }

    public void updateData(List<PojoForMeal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.card_daily_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        PojoForMeal meal = meals.get(position);
        holder.mealTextView.setText(meal.getMealName());
        holder.counterNameTextView.setText(meal.getAreaOfMeal());

        // Load image using Glide
        Glide.with(context)
                .load(meal.getImageUrl()) // Load image from the provided URL
                .placeholder(R.drawable.logo2) // Placeholder image while loading
                .error(R.drawable.logo2) // Error image if loading fails
                .into(holder.mealImageView); // ImageView to load the image into
    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealTextView;
        TextView counterNameTextView;
        ImageView mealImageView;

        MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealTextView = itemView.findViewById(R.id.meal_name);
            counterNameTextView = itemView.findViewById(R.id.meal_country);
            mealImageView = itemView.findViewById(R.id.meal_image);
        }
    }
}
