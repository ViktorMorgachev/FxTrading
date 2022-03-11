package com.fx_trading.lessons.feature_common

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


fun ImageView.loadImage(
    imageUrl: String,
    context: Context,
    elseAction: (() -> Unit)? = null
) {
    Glide.with(context)
        .load(imageUrl).addListener(requestListener)
        .into(this)
}