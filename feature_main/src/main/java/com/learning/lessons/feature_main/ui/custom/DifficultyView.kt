package com.learning.lessons.feature_main.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.StarsItemBinding

class DifficultyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private val binding = StarsItemBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setDifficulty(difficultyID: Int){
        with(binding){
            when (difficultyID) {
                1 -> {
                    star1.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.star_orange,
                            null
                        )
                    )
                }
                2 -> {
                   star1.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.star_orange,
                            null
                        )
                    )
                    star2.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.star_orange,
                            null
                        )
                    )
                }
                3 -> {
                    star1.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.star_orange,
                            null
                        )
                    )
                    star2.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.star_orange,
                            null
                        )
                    )
                    star3.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.star_orange,
                            null
                        )
                    )
                }
            }
        }
    }


}