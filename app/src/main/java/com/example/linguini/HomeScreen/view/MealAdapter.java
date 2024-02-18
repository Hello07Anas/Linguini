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
import com.bumptech.glide.request.RequestOptions;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.R;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private List<PojoForMeal> mealList;
    private Context context;

    public MealAdapter(Context context, List<PojoForMeal> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    public void setMeals(List<PojoForMeal> meals) {
        this.mealList = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_daily_meal_without_btn, parent, false);
        return new MealViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        PojoForMeal pojoForMeal = mealList.get(position);
        holder.bind(pojoForMeal);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {

        private TextView mealNameTextView;
        private ImageView meal_image;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.meal_name);
            meal_image = itemView.findViewById(R.id.meal_image);
        }

        public void bind(PojoForMeal pojoForMeal) {
            mealNameTextView.setText(pojoForMeal.getMealName());

            // Load image using Glide
            Glide.with(itemView.getContext())
                    .load(pojoForMeal.getImageUrl())
                    .apply(new RequestOptions().centerCrop())  // Apply additional options if needed
                    .into(meal_image);
        }
    }
}
