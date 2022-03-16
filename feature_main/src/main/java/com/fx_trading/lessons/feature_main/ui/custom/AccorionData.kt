package com.fx_trading.lessons.feature_main.ui.custom

import androidx.recyclerview.widget.RecyclerView
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsAdapter

open  class AccordionData<out T>(val accordionTittle: String, val accordionListAdapter: T, var expanded: Boolean = false)

class LessonAccordionData(accordionTittle: String, accordionListAdapter: LessonsAdapter, expanded: Boolean = false): AccordionData<LessonsAdapter>(accordionTittle, accordionListAdapter, expanded)
