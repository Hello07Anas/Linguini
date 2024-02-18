package com.example.linguini.HomeScreen.presenter;

import android.util.Log;

import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.network.NetworkCallBack;
import com.example.linguini.HomeScreen.model.repo.MealsRepository;
import com.example.linguini.HomeScreen.view.SearchView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchPresenterIMP implements SearchPresenter, NetworkCallBack.MealCallBackSearch {

    private static final String TAG = "SearchPresenterIMP";
    private SearchView view;
    private MealsRepository repository;
    private final PublishSubject<String> querySubject = PublishSubject.create();

    public SearchPresenterIMP(MealsRepository repository, SearchView view) {
        this.repository = repository;
        this.view = view;
        observeQueryChanges();
    }

    private void observeQueryChanges() {
        querySubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(this::search);
    }

    @Override
    public void onSuccessSearch(MealResponse mealResponse) {
        if (view != null) {
            Log.i(TAG, "onSuccessSearch: " + mealResponse.getMealDay().get(0).getMealName());
            view.showSearchResults(mealResponse);
        }
    }

    @Override
    public void onFailSearch(String error) {
        if (view != null) {
            view.showError(error);
            Log.i(TAG, "onFailSearch: " + error);
        }
    }

    @Override
    public void getSearchMeals(String key) {
        Log.i(TAG, "getSearchMeals: " + key + "HEere");
        repository.getSearchByName(key, this);
    }

    @Override
    public void search(String query) {
        getSearchMeals(query);
    }

    // Assume this method is called whenever the user types or removes a character
    public void onSearchQueryChanged(String query) {
        Observable.just(query)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: " + d + "here");
                        // Disposable is not used in this example
                    }

                    @Override
                    public void onNext(String query) {
                        search(query);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Handle error if any
                    }

                    @Override
                    public void onComplete() {
                        // Handle completion if needed
                    }
                });
    }
}
