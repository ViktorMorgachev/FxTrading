package com.fx_trading.lessons.utils.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import kotlin.coroutines.CoroutineContext

fun View.visibleOrGone(visible: Boolean){
    if (visible) {
        this.visibility = View.VISIBLE
    } else   this.visibility = View.GONE
}
fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

