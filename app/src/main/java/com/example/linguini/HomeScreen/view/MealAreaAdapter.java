package com.example.linguini.HomeScreen.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;
import com.example.linguini.R;

import java.util.List;

public class MealAreaAdapter extends RecyclerView.Adapter<MealAreaAdapter.ViewHolder> {

    private List<PojoForMealArea> mealAreas;

    public MealAreaAdapter(List<PojoForMealArea> mealAreas) {
        this.mealAreas = mealAreas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_area, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PojoForMealArea mealArea = mealAreas.get(position);

        // Set country name (emoji flag) in TextView
        holder.country_name.setText(getFlagEmoji(mealArea.getStrArea()) + " " + mealArea.getStrArea());

        // Set emoji flag in ImageView
        Drawable emojiDrawable = getDrawableFromEmoji(getFlagEmoji(mealArea.getStrArea()), holder.itemView);
        if (emojiDrawable != null) {
            holder.country_img.setImageDrawable(emojiDrawable);
        }
    }


    @Override
    public int getItemCount() {
        return mealAreas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView country_name;
        ImageView country_img;


        ViewHolder(View itemView) {
            super(itemView);
            country_name = itemView.findViewById(R.id.country_name);
            country_img = itemView.findViewById(R.id.country_image);
        }
    }

    public void updateData(List<PojoForMealArea> newMealAreas) {
        this.mealAreas.clear();
        this.mealAreas.addAll(newMealAreas);
        notifyDataSetChanged();
    }

    private Drawable getDrawableFromEmoji(String emoji, View itemView) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(48); // Adjust text size as needed
        paint.setTextAlign(Paint.Align.LEFT);
        int width = (int) paint.measureText(emoji);
        int height = (int) (-paint.ascent() + paint.descent());
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(emoji, 0, -paint.ascent(), paint);
        return new BitmapDrawable(itemView.getResources(), bitmap);
    }


    // Method to get emoji flag based on country code
    private String getFlagEmoji(String countryName) {
        switch (countryName.toLowerCase()) {
            case "american":
                return "\uD83C\uDDFA\uD83C\uDDF8"; // United States flag emoji
            case "british":
                return "\uD83C\uDDEC\uD83C\uDDE7"; // United Kingdom flag emoji
            case "canadian":
                return "\uD83C\uDDE8\uD83C\uDDE6"; // Canada flag emoji
            case "chinese":
                return "\uD83C\uDDE8\uD83C\uDDF3"; // China flag emoji
            case "croatian":
                return "\uD83C\uDDED\uD83C\uDDF7"; // Croatia flag emoji
            case "dutch":
                return "\uD83C\uDDF3\uD83C\uDDF1"; // Netherlands flag emoji
            case "egyptian":
                return "\uD83C\uDDEA\uD83C\uDDEC"; // Egypt flag emoji
            case "filipino":
                return "\uD83C\uDDEB\uD83C\uDDEE"; // Philippines flag emoji
            case "french":
                return "\uD83C\uDDEB\uD83C\uDDF7"; // France flag emoji
            case "greek":
                return "\uD83C\uDDEC\uD83C\uDDF7"; // Greece flag emoji
            case "indian":
                return "\uD83C\uDDEE\uD83C\uDDF3"; // India flag emoji
            case "irish":
                return "\uD83C\uDDEE\uD83C\uDDEA"; // Ireland flag emoji
            case "italian":
                return "\uD83C\uDDEE\uD83C\uDDF9"; // Italy flag emoji
            case "jamaican":
                return "\uD83C\uDDEF\uD83C\uDDF2"; // Jamaica flag emoji
            case "japanese":
                return "\uD83C\uDDEF\uD83C\uDDF5"; // Japan flag emoji
            case "kenyan":
                return "\uD83C\uDDF0\uD83C\uDDF2"; // Kenya flag emoji
            case "malaysian":
                return "\uD83C\uDDF2\uD83C\uDDFE"; // Malaysia flag emoji
            case "mexican":
                return "\uD83C\uDDF2\uD83C\uDDFD"; // Mexico flag emoji
            case "moroccan":
                return "\uD83C\uDDF2\uD83C\uDDE6"; // Morocco flag emoji
            case "polish":
                return "\uD83C\uDDF5\uD83C\uDDF1"; // Poland flag emoji
            case "portuguese":
                return "\uD83C\uDDF5\uD83C\uDDF9"; // Portugal flag emoji
            case "russian":
                return "\uD83C\uDDF7\uD83C\uDDFA"; // Russia flag emoji
            case "spanish":
                return "\uD83C\uDDEA\uD83C\uDDF8"; // Spain flag emoji
            case "thai":
                return "\uD83C\uDDF9\uD83C\uDDED"; // Thailand flag emoji
            case "tunisian":
                return "\uD83C\uDDF9\uD83C\uDDF3"; // Tunisia flag emoji
            case "turkish":
                return "\uD83C\uDDF9\uD83C\uDDF7"; // Turkey flag emoji
            case "palastine":
                return "\uD83C\uDDF5\uD83C\uDDF8"; // Palestine flag emoji
            case "vietnamese":
                return "\uD83C\uDDFB\uD83C\uDDF3"; // Vietnam flag emoji
            default:
                return "\uD83C\uDDF5\uD83C\uDDF8"; //
        }
    }

}


//package com.example.linguini.HomeScreen.view;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;
//import com.example.linguini.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MealAreaAdapter extends RecyclerView.Adapter<MealAreaAdapter.ViewHolder> {
//
//    private List<PojoForMealArea> mealAreas;
//
//    public MealAreaAdapter(List<PojoForMealArea> mealAreas) {
//        this.mealAreas = mealAreas;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_area, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        PojoForMealArea mealArea = mealAreas.get(position);
//
//        // Glide or Picasso to load image (add necessary dependencies)
//        Glide.with(holder.country_image)
//                .load(mealArea.getStrArea()) // Assuming the object has an "imageUrl" attribute
//                .into(holder.country_image);
//
//        holder.country_name.setText(mealArea.getStrArea());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mealAreas.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView country_image;
//        TextView country_name;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            country_image = itemView.findViewById(R.id.country_image);
//            country_name = itemView.findViewById(R.id.country_name);
//        }
//    }
//
//    public void updateData(List<PojoForMealArea> newMealAreas) {
//        this.mealAreas.clear();
//        this.mealAreas.addAll(newMealAreas);
//        notifyDataSetChanged();
//    }
//}

