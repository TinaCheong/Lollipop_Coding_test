package com.tina.lollipopcodingtest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class NewsListResult(
    val data : NewsList
) : Parcelable