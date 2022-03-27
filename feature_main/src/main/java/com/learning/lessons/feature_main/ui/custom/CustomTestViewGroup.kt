package com.learning.lessons.feature_main.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.learning.lessons.features.databinding.CustomTestViewGroupBinding

class CustomTestViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr){


    val constraintLayoutParams =  ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val linearLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    private val binding = CustomTestViewGroupBinding.inflate(LayoutInflater.from(this.context))

    init {
        addView(binding.root, linearLayoutParams)
    }

    fun setText(text: String){
        with(binding){
            testButton.text = text
        }
    }

}