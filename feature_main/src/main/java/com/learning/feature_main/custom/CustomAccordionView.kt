package com.learning.feature_main.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.lessons.features.databinding.AccordionViewBinding
import com.learning.lessons.utils.utils.gone


class CustomAccordionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var collapsed: Boolean = false

    private val binding = AccordionViewBinding.inflate(LayoutInflater.from(context))

    val constraintLayoutParams =  ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val linearLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    init {
        addView(binding.root, constraintLayoutParams)
    }

    fun initData(recyclerAdapter: RecyclerView.Adapter<*>, accordionTittle: String) {
        with(binding) {
            this.tvCategory.text = "Test"
            this.tvCategory.setTextColor(Color.BLACK)
            this.ivArrow.setOnClickListener {
                if (collapsed) {
                    expandView()
                } else expandView()
            }
            accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
            accordionsRecyclerView.adapter = recyclerAdapter
        }

    }

    fun setTittle(accordionTittle: String) {
        with(binding) {
            tvCategory.text = accordionTittle
        }
    }

    fun expandView() {
        with(binding) {
            ivArrow.rotation = 180F
            accordionsRecyclerView.gone()
            collapsed = true
        }

    }

    fun collapseView() {
        with(binding) {
            ivArrow.rotation = 0F
            accordionsRecyclerView.gone()
            collapsed = false
        }

    }


}