package com.dicoding.restaurantreview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostReviewResponse {
    @SerializedName("customerReviews")
    private List<ConsumerReviewsItem> consumerReviews;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<ConsumerReviewsItem> getConsumerReviews(){
        return consumerReviews;
    }

    public boolean isError(){
        return error;
    }

    public String getMessage(){
        return message;
    }
}
