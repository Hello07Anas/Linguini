package com.example.linguini.HomeScreen.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;
import com.example.linguini.R;

import java.util.List;

public class MealAreaAdapter extends RecyclerView.Adapter<MealAreaAdapter.ViewHolder> {

    private List<PojoForMealArea> cardArea;

    public MealAreaAdapter(List<PojoForMealArea> mealAreas) {
        this.cardArea = mealAreas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_area, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PojoForMealArea mealArea = cardArea.get(position);

        // Glide or Picasso to load image (add necessary dependencies)
        Glide.with(holder.country_image)
                .load(mealArea.getStrArea()) // Assuming the object has an "imageUrl" attribute
                .into(holder.country_image);

        holder.country_name.setText(mealArea.getStrArea());
    }

    @Override
    public int getItemCount() {
        return cardArea.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView country_image;
        TextView country_name;

        ViewHolder(View itemView) {
            super(itemView);
            country_image = itemView.findViewById(R.id.country_image);
            country_name = itemView.findViewById(R.id.country_name);
        }
    }
}
