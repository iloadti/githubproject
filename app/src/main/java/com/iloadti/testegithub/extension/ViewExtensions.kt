package com.iloadti.testegithub.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iloadti.testegithub.R

internal fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

internal fun ImageView.loadUrl(url: String?) {
    val options = RequestOptions().circleCrop().error(R.drawable.ic_broken_image_black_24dp)

    Glide
        .with(context)
        .load(url)
        .apply(options)
        .into(this)
}