package com.example.linguini.HomeScreen.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
import com.example.linguini.HomeScreen.presenter.DetailsPresenterIMP;
import com.example.linguini.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class DetailsFragment extends Fragment implements DetailsView {
    private String mealId;
    private DetailsPresenterIMP detailsPresenterIMP;
    private static final String TAG = "DetailsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsPresenterIMP = new DetailsPresenterIMP(this, MealsRepoIMP.getInstance(
                MealsRemoteDataSourceIMP.getInstance(getContext())
        ));

        mealId = DetailsFragmentArgs.fromBundle(getArguments()).getSelectedMeal();
        Log.i(TAG, "onCreate: " + mealId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        detailsPresenterIMP.getMealDetails(mealId);

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                Log.i(TAG, "YouTube Player is ready.");
            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
                Log.e(TAG, "YouTube Player Error: " + error.toString());
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Any additional initialization code can go here
    }





    @Override
    public void showDetailse(MealResponse mealResponse) {
        Log.i(TAG, "show Meal: " + mealResponse.getMealDay().get(0).getMealName());

        // Retrieve meal details from the response
        PojoForMeal pojoForMeal = mealResponse.getMealDay().get(0);

        // Populate UI elements with meal data
        TextView mealNameTextView = getView().findViewById(R.id.meal_name);
        TextView mealCountryTextView = getView().findViewById(R.id.meal_country);
        ImageView mealImageView = getView().findViewById(R.id.meal_image);

        mealNameTextView.setText(pojoForMeal.getMealName());
        mealCountryTextView.setText(pojoForMeal.getAreaOfMeal());
        Glide.with(getContext()).load(pojoForMeal.getImageUrl())
                .placeholder(R.drawable.logo2)
                .error(R.drawable.palastine)
                .into(mealImageView);

        // Extract video ID from the YouTube URL
        String videoUrl = pojoForMeal.getStrYoutube();
        String videoId = extractVideoIdFromUrl(videoUrl);

        // Initialize YouTubePlayerView and load the video
        YouTubePlayerView youTubePlayerView = getView().findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
                Log.e(TAG, "YouTube Player Error: " + error.toString());
            }
        });
    }

    @Override
    public void showDetailseErrorMSG(String error) {
        Log.i(TAG, "showArea: " + error);
    }

    @Override
    public void onClicked(PojoForMeal pojoForMeal, View view) {
        // Implement your logic here for handling clicks on meal items
    }

    // Utility method to extract video ID from YouTube URL
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





//package com.example.linguini.HomeScreen.view;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.bumptech.glide.Glide;
//import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
//import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
//import com.example.linguini.HomeScreen.model.network.MealService;
//import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSource;
//import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
//import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
//import com.example.linguini.HomeScreen.presenter.DetailsPresenterIMP;
//import com.example.linguini.HomeScreen.presenter.HomePresenter;
//import com.example.linguini.HomeScreen.presenter.HomePresenterIMP;
//import com.example.linguini.R;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
////import com.squareup.picasso.Picasso;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class DetailsFragment extends Fragment implements DetailsView {
//    private String mealId;
//    DetailsPresenterIMP detailsPresenterIMP;
//    private static final String TAG = "DetailsFragment";
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        detailsPresenterIMP = new DetailsPresenterIMP(this, MealsRepoIMP.getInstance(
//                MealsRemoteDataSourceIMP.getInstance(getContext())
//        ));
//
//
//        mealId = DetailsFragmentArgs.fromBundle(getArguments()).getSelectedMeal();
//        Log.i(TAG, "onCreate: " + mealId);
//
//    }
//
//
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_details, container, false);
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        detailsPresenterIMP.getMealDetails(mealId);
//
//
//
//        // TODO <<<  find by id
//
//    }
//
//
//    @Override
//    public void showDetailse(MealResponse mealResponse) {
//        Log.i(TAG, "show Meal: " + mealResponse.getMealDay().get(0).getMealName());
//
//        // Retrieve meal details from the response
//        PojoForMeal pojoForMeal = mealResponse.getMealDay().get(0); // Assuming mealDay is a list containing meals, adjust this accordingly
//
//        // Populate UI elements with meal data
//        TextView mealNameTextView = getView().findViewById(R.id.meal_name);
//        TextView mealCountryTextView = getView().findViewById(R.id.meal_country);
//        ImageView mealImageView = getView().findViewById(R.id.meal_image);
//
//        mealNameTextView.setText(pojoForMeal.getMealName());
//        mealCountryTextView.setText(pojoForMeal.getAreaOfMeal());
//
//        Glide.with(getContext()).load(pojoForMeal.getImageUrl())
//                .placeholder(R.drawable.logo2)
//                .error(R.drawable.palastine)
//                .into(mealImageView);
//
//        String videoUrl = pojoForMeal.getStrYoutube();
//        Log.d(TAG, "YouTube Video URL: " + videoUrl);
//        String videoId = extractVideoIdFromUrl(videoUrl);
//        Log.d(TAG, "Extracted Video ID: " + videoId);
//
//
//        YouTubePlayerView youTubePlayerView = getView().findViewById(R.id.youtube_player_view);
//        getLifecycle().addObserver(youTubePlayerView);
//
//        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                youTubePlayer.loadVideo(videoId, 0);
//            }
//
//            @Override
//            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
//                super.onError(youTubePlayer, error);
//                Log.e(TAG, "YouTube Player Error: " + error.toString());
//            }
//        });
//    }
//
//
//    @Override
//    public void showDetailseErrorMSG(String error) {
//        Log.i(TAG, "showArea: " + error);
//    }
//
//    @Override
//    public void onClicked(PojoForMeal pojoForMeal, View view) {
//
//    }
//
//
//// Youtube video <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//    private String extractVideoIdFromUrl(String youtubeUrl) {
//        String videoId = null;
//        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("https://www.youtube.com/")) {
//            String[] urlParts = youtubeUrl.split("v=");
//            if (urlParts.length > 1) {
//                videoId = urlParts[1];
//                int ampersandPosition = videoId.indexOf('&');
//                if (ampersandPosition != -1) {
//                    videoId = videoId.substring(0, ampersandPosition);
//                }
//            }
//        }
//        return videoId;
//    }
//
//}
