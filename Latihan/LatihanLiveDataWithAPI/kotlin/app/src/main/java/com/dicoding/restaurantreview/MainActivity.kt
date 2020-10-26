package com.dicoding.restaurantreview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mainViewModel.findRestaurant()
        mainViewModel.restaurant.observe(this, Observer { restaurant ->
            tvTitle.text = restaurant.name
            tvDescription.text = "${restaurant.description.take(100)}..."
            Glide.with(this).load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}").into(ivPicture)
        })

        mainViewModel.listReview.observe(this, Observer { consumerReviews ->
            val listReview = ArrayList<String>()
            consumerReviews.forEach {
                listReview.add("${it.review}\n- ${it.name}")
            }
            lvReview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listReview)
        })

        mainViewModel.isLoading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        btnSend.setOnClickListener { view ->
            mainViewModel.postReview(edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}