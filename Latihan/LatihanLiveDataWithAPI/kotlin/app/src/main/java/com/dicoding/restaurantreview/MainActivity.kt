package com.dicoding.restaurantreview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.restaurantreview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportActionBar?.hide()

        mainViewModel.findRestaurant()
        mainViewModel.restaurant.observe(this, { restaurant ->
            activityMainBinding.tvTitle.text = restaurant.name
            activityMainBinding.tvDescription.text = "${restaurant.description.take(100)}..."
            Glide.with(this)
                .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
                .into(activityMainBinding.ivPicture)
        })

        mainViewModel.listReview.observe(this, { consumerReviews ->
            val listReview = consumerReviews.map {
                "${it.review}\n- ${it.name}"
            }

            activityMainBinding.lvReview.adapter =
                ArrayAdapter(this, R.layout.item_review, listReview)
        })

        mainViewModel.isLoading.observe(this, {
            activityMainBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        activityMainBinding.btnSend.setOnClickListener { view ->
            mainViewModel.postReview(activityMainBinding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}