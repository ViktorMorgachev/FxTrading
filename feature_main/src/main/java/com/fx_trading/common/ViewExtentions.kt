package com.fx_trading.common

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.fx_trading.lessons.features.R


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
    return intent.extras?.get(key) as Int? ?: defaultValue
}

fun TextView.setDifficulty(difficulty: Int){
    when(difficulty){
        1 -> {
           this.text = resources.getString(R.string.beginner)
        }
        2 -> {
            this.text = resources.getString(R.string.intermediate)
        }
        3 -> {
            this.text = resources.getString(R.string.experienced)
        }
    }
}