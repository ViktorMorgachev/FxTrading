package com.fx_trading.lessons.feature_main.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.AccordionListViewBinding

class CustomAccordionListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = AccordionListViewBinding.inflate(LayoutInflater.from(context))
    val constraintLayoutParams =  ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val linearLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    init {
       addView(binding.root, constraintLayoutParams)
    }

    fun <T> setLessonData(data: List<LessonAccordionData>, accordionCollapsedAction: () -> Unit, accordionExpandedAction: () -> Unit, accordionOnItemClickAction: (T) -> Unit){
        with(binding) {
            this.accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
            accordionsRecyclerView.adapter = BaseAccordionAdapter(data = data, accordionCollapsedAction, accordionExpandedAction)
        }
    }

}