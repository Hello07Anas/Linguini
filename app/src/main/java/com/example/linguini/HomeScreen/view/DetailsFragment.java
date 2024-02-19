package com.example.linguini.HomeScreen.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.dataBase.MealDataBase;
import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
import com.example.linguini.HomeScreen.presenter.DetailsPresenterIMP;
import com.example.linguini.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsFragment extends Fragment implements DetailsView {
    private String mealId;
    private DetailsPresenterIMP detailsPresenterIMP;
    String videoId;
    ImageView imgGif;
    ImageButton btnFav;
    PojoForMeal pojoForMeal;
    private static final String TAG = "DetailsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailsPresenterIMP = new DetailsPresenterIMP(this, MealsRepoIMP.getInstance(
                MealsRemoteDataSourceIMP.getInstance(getContext())
        ));
        mealId = DetailsFragmentArgs.fromBundle(getArguments()).getSelectedMeal();
        //Log.i(TAG, "onCreate: " + mealId);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        detailsPresenterIMP.getMealDetails(mealId);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnFav = view.findViewById(R.id.btnFav);

        // DB
        MealDataBase mealDataBase = MealDataBase.getInstance(getContext());
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mealDataBase == null) {
                    Log.e(TAG, "Database instance is null");
                    return;
                }

                if (pojoForMeal == null) {
                    Log.e(TAG, "pojoForMeal is null");
                    return;
                }

                List<String> dataList = new ArrayList<>();
                dataList.add("data1");
                dataList.add("data2");
                dataList.add("data3");

                PojoForMeal newPojoForMeal = new PojoForMeal(
                        pojoForMeal.getIdMeal(),
                        pojoForMeal.getMealName(),
                        pojoForMeal.getStrCategory(),
                        pojoForMeal.getAreaOfMeal(),
                        pojoForMeal.getStrInstructions(),
                        pojoForMeal.getImageUrl(),
                        pojoForMeal.getStrYoutube(),
                        dataList,
                        dataList
                );

                mealDataBase.mealDAO().insertMeal(newPojoForMeal)
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            }

                            @Override
                            public void onComplete() {
                                requireActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "WonderFull Choice \uD83D\uDC4D", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.e(TAG, "Error inserting meal into database", e);
                            }
                        });
            }
        });
    }

    @Override
    public void showDetailse(MealResponse mealResponse) {
        //Log.i(TAG, "show Meal: " + mealResponse.getMealDay().get(0).getMealName());
        pojoForMeal = mealResponse.getMealDay().get(0);

        Log.i(TAG, "ingredients: " + pojoForMeal.getIngredients());

        TextView mealNameTextView = getView().findViewById(R.id.meal_name);
        TextView mealCountryTextView = getView().findViewById(R.id.meal_country);
        TextView txtInstructions = getView().findViewById(R.id.txtInstructions);
        ImageView mealImageView = getView().findViewById(R.id.meal_image);

        mealNameTextView.setText(pojoForMeal.getMealName());
        mealCountryTextView.setText(pojoForMeal.getAreaOfMeal());

        Glide.with(getContext()).load(pojoForMeal.getImageUrl())
                .placeholder(R.drawable.logo2)
                .error(R.drawable.palastine)
                .into(mealImageView);

        imgGif = getView().findViewById(R.id.imgGif);
        Glide.with(requireContext())
                .asGif()
                .load(R.drawable.mlaha)
                .placeholder(R.drawable.palastine)
                .error(R.drawable.palastine)
                .into(imgGif);

        String videoUrl = pojoForMeal.getStrYoutube();
        videoId = extractVideoIdFromUrl(videoUrl);
        Log.i(TAG, "showDetailse: " + videoId);
        YouTubePlayerView youTubePlayerView = getView().findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0);
                Log.i(TAG, "onReady: " + videoId);
            }
            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
                Log.e(TAG, "YouTube Player Error: " + error.toString());
            }
        });

        List<String> ingredients = pojoForMeal.getIngredients();

        if (ingredients != null) {
            Log.i(TAG, "Number of Ingredients: " + ingredients.size());

            for (int i = 0; i < Math.min(ingredients.size(), 12); i++) {
                int ingredientTextViewId = getResources().getIdentifier("strIngredient" + (i + 1), "id", requireContext().getPackageName());
                TextView ingredientTextView = getView().findViewById(ingredientTextViewId);
                Log.d(TAG, "Setting Ingredient " + (i + 1) + ": " + ingredients.get(i));
                if (ingredientTextView != null) {
                    ingredientTextView.setText(ingredients.get(i));
                } else {
                    Log.e(TAG, "Ingredient TextView not found for index: " + (i + 1));
                }
            }
        } else {
            Log.e(TAG, "Ingredients list is null");
        }
    }

    @Override
    public void showDetailseErrorMSG(String error) {
        Log.i(TAG, "showArea: " + error);
    }

    @Override
    public void onClicked(PojoForMeal pojoForMeal, View view) {
    }

    private String extractVideoIdFromUrl(String youtubeUrl) {
        String videoId = null;
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("https://www.youtube.com/")) {
            String[] urlParts = youtubeUrl.split("v=");
            if (urlParts.length > 1) {
                videoId = urlParts[1];
                int ampersandPosition = videoId.indexOf('&');
                if (ampersandPosition != -1) {
                    videoId = videoId.substring(0, ampersandPosition);
                }
            }
        }
        return videoId;
    }
}