package com.dicoding.restaurantreview;

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
import com.google.android.material.snackbar.Snackbar;

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

        mainViewModel.getRestaurant().observe(this, restaurant -> {
            activityMainBinding.tvTitle.setText(restaurant.getName());
            activityMainBinding.tvDescription.setText(restaurant.getDescription());
            Glide.with(this).
                    load("https://restaurant-api.dicoding.dev/images/large/" + restaurant.getPictureId())
                    .into(activityMainBinding.ivPicture);
        });

        mainViewModel.getListReview().observe(this, consumerReviews -> {
            ArrayList<String> listReview = new ArrayList<>();
            for (CustomerReviewsItem review : consumerReviews) {
                listReview.add(review.getReview() + "\n- " + review.getName());
            }
            activityMainBinding.lvReview.setAdapter(new ArrayAdapter<>(this, R.layout.item_review, listReview));
            activityMainBinding.edReview.setText("");
        });

        mainViewModel.isLoading().observe(this, isLoading -> {
            if (isLoading) {
                activityMainBinding.progressBar.setVisibility(View.VISIBLE);
            } else {
                activityMainBinding.progressBar.setVisibility(View.GONE);
            }
        });

        mainViewModel.snackbarText().observe(this, text -> {
//            Snackbar.make(
//                    findViewById(R.id.parent_layout),
//                    text,
//                    Snackbar.LENGTH_SHORT
//            ).show();
            String snackBarText = text.getContentIfNotHandled();
            if (snackBarText != null) {
                Snackbar.make(
                        findViewById(R.id.parent_layout),
                        snackBarText,
                        Snackbar.LENGTH_SHORT
                ).show();
            }
        });

        activityMainBinding.btnSend.setOnClickListener(view -> {
            mainViewModel.postReview(activityMainBinding.edReview.getText().toString());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }
}