package com.learning.lessons.feature_main.ui.custom

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.lessons.feature_main.ui.lessons.LessonsAdapter
import com.learning.lessons.features.databinding.AccordionListViewBinding
import com.learning.lessons.utils.utils.Logger

class CustomAccordionListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var accCollapsedAction: () -> Unit
    private lateinit var accordionExpandedAction: () -> Unit
    private val binding = AccordionListViewBinding.inflate(LayoutInflater.from(context))


    val constraintLayoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    init {
        addView(binding.root, constraintLayoutParams)

        with(binding){
             accordionExpandedAction = {
                 try {
                     val lastAdapter = accordionsRecyclerView.adapter as BaseAccordionListAdapter<*>
                     val lastData = lastAdapter.dataList as List<AccordionData<LessonsAdapter>>
                     val newAdapter = BaseAccordionListAdapter(dataList = lastData, accCollapsedAction, accordionExpandedAction)
                     accordionsRecyclerView.adapter = newAdapter
                 } catch (e: Exception){
                     Logger.log("CustomAccordionListView", exception = e)
                 }
             }
             accCollapsedAction = { accordionsRecyclerView.invalidate() }
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun  <A : RecyclerView.Adapter<*>, T> updateData(data: T){
        with(binding){
            val lastAdapter = accordionsRecyclerView.adapter as BaseAccordionListAdapter<*>
            lastAdapter.updateData<A, T>(data)
        }
    }

    fun setLessonsData(
        data: List<LessonAccordionData>
    ) {
        with(binding) {
            this.accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
            accordionsRecyclerView.adapter = BaseAccordionListAdapter(dataList = data, accCollapsedAction, accordionExpandedAction)
        }
    }

    fun setCousesData(
        data: List<CourseAccordionData>
    ) {
        with(binding) {
            this.accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
            accordionsRecyclerView.adapter = BaseAccordionListAdapter(dataList = data, accCollapsedAction, accordionExpandedAction)
        }
    }

}