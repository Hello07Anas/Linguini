package com.example.linguini.HomeScreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.R;
import java.util.List;

public class MealOfDayAdapter extends RecyclerView.Adapter<MealOfDayAdapter.MealViewHolder> {
    private List<PojoForMeal> meals;
    private Context context;
    private HomeView view;
    private OnDeleteClickListener onDeleteClickListener;

    public MealOfDayAdapter(List<PojoForMeal> meals, HomeView view) {
        this.meals = meals;
        this.view = view;
    }

    public void updateData(List<PojoForMeal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }
    public List<PojoForMeal> getData() {
        return meals;
    }

    public void removeItem(int position) {
        meals.remove(position);
        notifyItemRemoved(position);
    }
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
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
        holder.layOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.onClicked(meals.get(holder.getAdapterPosition()), v);
            }
        });
        // Load image using Glide
        Glide.with(context)
                .load(meal.getImageUrl())
                .placeholder(R.drawable.palastine)
                .error(R.drawable.palastine)
                .into(holder.mealImageView);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Done ✅", Toast.LENGTH_SHORT).show();
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.onDeleteClick(clickedPosition);
                    }
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealTextView;
        TextView counterNameTextView;
        ImageView mealImageView;
        ImageButton btnDelete;

        View layOut;

        MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealTextView = itemView.findViewById(R.id.meal_name);
            counterNameTextView = itemView.findViewById(R.id.meal_country);
            mealImageView = itemView.findViewById(R.id.meal_image);
            btnDelete = itemView.findViewById(R.id.btnDeletes);
            layOut = itemView;
        }
    }
}