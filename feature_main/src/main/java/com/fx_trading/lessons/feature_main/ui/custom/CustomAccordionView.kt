package com.fx_trading.lessons.feature_main.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.AccordionViewBinding
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.visible


class CustomAccordionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val accordionNameFromView: String?

    private val binding = AccordionViewBinding.inflate(LayoutInflater.from(context))

    init {
        inflate(context, R.layout.accordion_view, this)
        val ta: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CustomAccordionView, 0, 0)
        accordionNameFromView = ta.getString(R.styleable.CustomAccordionView_accordionName)
        with(binding) {
            tvCategory.text = accordionNameFromView ?: ""
        }
        ta.recycle()
    }

    @SuppressWarnings("unchecked")
    fun initData(recyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(this.root.context)
            recyclerView.adapter = recyclerAdapter
        }

    }

    fun expandView() {
        with(binding) {
            ivArrow.rotation = 180F
            recyclerView.visible()
        }

    }

    fun collapseView() {
        with(binding) {
            ivArrow.rotation = 0F
            recyclerView.gone()
        }

    }

}