package com.fx_trading.common

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