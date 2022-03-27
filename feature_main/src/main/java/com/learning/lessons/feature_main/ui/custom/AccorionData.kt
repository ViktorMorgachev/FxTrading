package com.learning.lessons.feature_main.ui.custom

import androidx.recyclerview.widget.RecyclerView
import com.learning.lessons.feature_main.ui.lessons.LessonsAdapter

open class AccordionData<T: RecyclerView.Adapter<*>>(val accordionTittle: String, var accordionListAdapter: RecyclerView.Adapter<*>, var recyclerView: RecyclerView? = null, position: Int = 0)

class LessonAccordionData(accordionTittle: String, accordionListAdapter: LessonsAdapter, recyclerView: RecyclerView? = null): AccordionData<LessonsAdapter>(accordionTittle, accordionListAdapter, recyclerView)
