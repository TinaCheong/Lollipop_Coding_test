package com.tina.lollipopcodingtest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class News(
    val title: String = "",
    val thumbnail: String = "",
    val id: String = "",
    val name: String = "",
    val created_utc: Long = 1
) : Parcelable