package com.dicoding.restaurantreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.dicoding.restaurantreview.databinding.ActivityMainBinding;
import com.dicoding.restaurantreview.model.CustomerReviewsItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.findRestaurant();

        mainViewModel.getRestaurant().observe(this, restaurant -> {
            activityMainBinding.tvTitle.setText(restaurant.getName());
            activityMainBinding.tvDescription.setText(restaurant.getDescription().substring(0, 100) + "...");
            Glide.with(this).
                    load("https://restaurant-api.dicoding.dev/images/large/" + restaurant.getPictureId())
                    .into(activityMainBinding.ivPicture);
        });

        mainViewModel.getListReview().observe(this, customerReviews -> {
            ArrayList<String> listReview = new ArrayList<>();
            for (ConsumerReviewsItem review : consumerReviews) {
                listReview.add(review.getReview()+"\n- "+review.getName());
            }
            activityMainBinding.lvReview.setAdapter(new ArrayAdapter<>(this, R.layout.item_review, listReview));
        });

        mainViewModel.isLoading().observe(this, isLoading -> {
            if (isLoading) {
                activityMainBinding.progressBar.setVisibility(View.VISIBLE);
            } else {
                activityMainBinding.progressBar.setVisibility(View.GONE);
            }
        });

        activityMainBinding.btnSend.setOnClickListener(view -> {
            mainViewModel.postReview(activityMainBinding.edReview.getText().toString());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }
}