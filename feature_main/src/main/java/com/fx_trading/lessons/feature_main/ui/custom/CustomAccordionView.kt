package com.fx_trading.lessons.feature_main.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.fx_trading.lessons.features.R


class CustomAccordionView: ConstraintLayout {
    constructor(context: Context) : super(context) {
        View.inflate(context, R.layout.accordion_view, this)

    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.accordion_view, this)
    }

    fun initData(){

    }

    fun expandView(){

    }

    fun collapseView(){

    }

}