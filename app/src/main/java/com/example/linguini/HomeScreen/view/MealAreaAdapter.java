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

        holder.country_name.setText(getFlagEmoji(mealArea.getStrArea()) + " " + mealArea.getStrArea());

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
        paint.setTextSize(200);
        paint.setTextAlign(Paint.Align.LEFT);
        int width = (int) paint.measureText(emoji);
        int height = (int) (-paint.ascent() + paint.descent());
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(emoji, 0, -paint.ascent(), paint);
        return new BitmapDrawable(itemView.getResources(), bitmap);
    }

    private String getFlagEmoji(String countryName) {
        switch (countryName.toLowerCase()) {
            case "american":
                return "\uD83C\uDDFA\uD83C\uDDF8";
            case "british":
                return "\uD83C\uDDEC\uD83C\uDDE7";
            case "canadian":
                return "\uD83C\uDDE8\uD83C\uDDE6";
            case "chinese":
                return "\uD83C\uDDE8\uD83C\uDDF3";
            case "croatian":
                return "\uD83C\uDDED\uD83C\uDDF7";
            case "dutch":
                return "\uD83C\uDDF3\uD83C\uDDF1";
            case "egyptian":
                return "\uD83C\uDDEA\uD83C\uDDEC";
            case "filipino":
                return "\uD83C\uDDEB\uD83C\uDDEE";
            case "french":
                return "\uD83C\uDDEB\uD83C\uDDF7";
            case "greek":
                return "\uD83C\uDDEC\uD83C\uDDF7";
            case "indian":
                return "\uD83C\uDDEE\uD83C\uDDF3";
            case "irish":
                return "\uD83C\uDDEE\uD83C\uDDEA";
            case "italian":
                return "\uD83C\uDDEE\uD83C\uDDF9";
            case "jamaican":
                return "\uD83C\uDDEF\uD83C\uDDF2";
            case "japanese":
                return "\uD83C\uDDEF\uD83C\uDDF5";
            case "kenyan":
                return "\uD83C\uDDF0\uD83C\uDDF2";
            case "malaysian":
                return "\uD83C\uDDF2\uD83C\uDDFE";
            case "mexican":
                return "\uD83C\uDDF2\uD83C\uDDFD";
            case "moroccan":
                return "\uD83C\uDDF2\uD83C\uDDE6";
            case "polish":
                return "\uD83C\uDDF5\uD83C\uDDF1";
            case "portuguese":
                return "\uD83C\uDDF5\uD83C\uDDF9";
            case "russian":
                return "\uD83C\uDDF7\uD83C\uDDFA";
            case "spanish":
                return "\uD83C\uDDEA\uD83C\uDDF8";
            case "thai":
                return "\uD83C\uDDF9\uD83C\uDDED";
            case "tunisian":
                return "\uD83C\uDDF9\uD83C\uDDF3";
            case "turkish":
                return "\uD83C\uDDF9\uD83C\uDDF7";
            case "palastine":
                return "\uD83C\uDDF5\uD83C\uDDF8";
            case "vietnamese":
                return "\uD83C\uDDFB\uD83C\uDDF3";
            default:
                return "\uD83C\uDDF5\uD83C\uDDF8";
        }
    }
}