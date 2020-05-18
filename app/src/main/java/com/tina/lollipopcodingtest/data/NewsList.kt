package com.tina.lollipopcodingtest.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsList(
    @Json(name = "children")
    val list : List<NewsData>
) : Parcelable