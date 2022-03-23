package com.fx_trading.common

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(
    imageUrl: String,
    context: Context,
    elseAction: (() -> Unit)? = null
) {
    Glide.with(context)
        .load(imageUrl).addListener(requestListener)
        .into(this)
}

fun Activity.getIntExtra(key: String, defaultValue: Int?): Int?{
    return intent.extras?.get(key) as Int ?: defaultValue
}