package com.tina.lollipopcodingtest

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tina.lollipopcodingtest.network.LoadApiStatus

@BindingAdapter("smallImage")
fun bindSmallImage(imgView: ImageView, thumbnail: String?) {
    thumbnail?.let {
        val uri = it.toUri().buildUpon().build()
        Glide.with(imgView.context)
            .load(uri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_small)
                    .error(R.drawable.placeholder_small)
            )
            .into(imgView)
    }
}

@BindingAdapter("largeImage")
fun bindLargeImage(imgView: ImageView, thumbnail: String?) {
    thumbnail?.let {
        val uri = it.toUri().buildUpon().build()
        Glide.with(imgView.context)
            .load(uri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_large)
                    .error(R.drawable.placeholder_large)
            )
            .into(imgView)
    }
}

@BindingAdapter("setupApiStatus")
fun bindApiStatus(view: ProgressBar, status: LoadApiStatus?) {
    when (status) {
        LoadApiStatus.LOADING -> view.visibility = View.VISIBLE
        LoadApiStatus.DONE, LoadApiStatus.ERROR -> view.visibility = View.GONE
    }
}