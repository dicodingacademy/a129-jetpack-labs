package com.dicoding.restaurantreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.restaurantreview.model.CustomerReviewsItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDescription = findViewById(R.id.tvDescription);
        ImageView ivPicture = findViewById(R.id.ivPicture);
        ListView lvReview = findViewById(R.id.lvReview);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button btnSend = findViewById(R.id.btnSend);
        EditText edReview = findViewById(R.id.edReview);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.findRestaurant();

        mainViewModel.getRestaurant().observe(this, restaurant -> {
            tvTitle.setText(restaurant.getName());
            tvDescription.setText(restaurant.getDescription().substring(0, 100)+"...");
            Glide.with(this).load("https://restaurant-api.dicoding.dev/images/large/"+restaurant.getPictureId()).into(ivPicture);
        });

        mainViewModel.getListReview().observe(this, customerReviews -> {
            ArrayList<String> listReview = new ArrayList<>();
            for (CustomerReviewsItem review : customerReviews){
                listReview.add(review.getReview()+"\n- "+review.getName());
            }
            lvReview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listReview));
        });

        mainViewModel.isLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        btnSend.setOnClickListener(view -> {
            mainViewModel.postReview(edReview.getText().toString());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }
}