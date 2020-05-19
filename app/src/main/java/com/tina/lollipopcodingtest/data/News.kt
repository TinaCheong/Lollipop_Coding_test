package com.tina.lollipopcodingtest.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "news_list_table")
data class News(

    @PrimaryKey(autoGenerate = true)
    val autoId: Int= 0,

    @ColumnInfo(name = "news_title")
    val title: String = "",

    @ColumnInfo(name = "news_thumbnail")
    val thumbnail: String = "",

    @ColumnInfo(name = "news_id")
    val id: String = "",

    @ColumnInfo(name = "news_name")
    val name: String = "",

    @ColumnInfo(name = "news_time")
    val created_utc: Long = 1

) : Parcelable