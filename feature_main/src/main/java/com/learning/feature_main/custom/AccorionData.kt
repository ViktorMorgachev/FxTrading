package com.learning.feature_main.custom

import androidx.recyclerview.widget.RecyclerView
import com.learning.feature_main.courses.CoursesAdapter
import com.learning.feature_main.lessons.LessonsAdapter

open class AccordionData<T: RecyclerView.Adapter<*>>(val accordionTittle: String, var accordionListAdapter: RecyclerView.Adapter<*>, var recyclerView: RecyclerView? = null, position: Int = 0)

class LessonAccordionData(accordionTittle: String, accordionListAdapter: LessonsAdapter, recyclerView: RecyclerView? = null): AccordionData<LessonsAdapter>(accordionTittle, accordionListAdapter, recyclerView)

class CourseAccordionData(accordionTittle: String, accordionListAdapter: CoursesAdapter, recyclerView: RecyclerView? = null): AccordionData<CoursesAdapter>(accordionTittle, accordionListAdapter, recyclerView)