package com.dicoding.academies.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseResponse(
        var id: String,
        var title: String,
        var description: String,
        var date: String,
        var imagePath: String
):Parcelable

