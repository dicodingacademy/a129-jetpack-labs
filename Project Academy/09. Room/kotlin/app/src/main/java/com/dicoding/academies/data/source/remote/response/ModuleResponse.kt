package com.dicoding.academies.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModuleResponse(
        var moduleId: String,
        var courseId: String,
        var title: String,
        var position: Int
) : Parcelable

