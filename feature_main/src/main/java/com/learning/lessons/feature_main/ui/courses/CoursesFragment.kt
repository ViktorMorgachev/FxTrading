package com.learning.lessons.feature_main.ui.courses

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.learning.common.State
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.entities.lesson.hasCategory
import com.learning.lessons.feature_main.ui.custom.CourseAccordionData
import com.learning.lessons.feature_main.ui.custom.LessonAccordionData
import com.learning.lessons.feature_main.ui.lessons.LessonsAdapter
import com.learning.lessons.feature_main.ui.lessons.LessonsViewModel
import com.learning.lessons.feature_main.ui.main.MainFragmentDirections
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentCoursesBinding
import com.learning.navigation.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoursesFragment : BaseFragment<FragmentCoursesBinding>() {
    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<CoursesViewModel>

    private val viewModel: CoursesViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @Inject
    lateinit var router: Router

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoursesBinding =
        FragmentCoursesBinding::inflate

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.getData().collect { state ->
                with(binding){
                    when (state) {
                        is State.DataState -> {
                            CoroutineScope(Dispatchers.IO).launch {
                                val allCourses = state.data.first

                                val categories = mutableListOf<Int>(1, 2, 3)

                                val accordionDataList = mutableListOf<CourseAccordionData>()
                                categories.forEach { categoryDifficulty ->
                                    val actualCourses = allCourses.filter { it.difficulty == categoryDifficulty }
                                    if (actualCourses.isEmpty()) return@forEach
                                    val coursesAdapter = CoursesAdapter(
                                        data = actualCourses,
                                        openCourseAction = { course ->
                                            NavHostFragment.findNavController(this@CoursesFragment).navigate(MainFragmentDirections.actionMainFragmentToCourseFragment(courseId = course.id))
                                        },
                                        likeCourseAction = {
                                            lifecycleScope.launchWhenResumed {
                                                viewModel.likeCourse(courseID = it).collect {
                                                    when(it){
                                                        is State.DataState ->{
                                                            with(binding) {
                                                                CoursesAdapter.actualCourses.add(it.data)
                                                                customAccordionList.updateData<CoursesAdapter, Course>(it.data)
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        },
                                        completedCoursesIDs = state.data.second,
                                    )
                                    val categoryName = when(categoryDifficulty){
                                        1 -> {
                                            resources.getString(R.string.course_category_beginner)
                                        }
                                        2 -> {
                                            resources.getString(R.string.course_category_intermediate)

                                        }
                                        3 -> {
                                            resources.getString(R.string.course_category_intermediate)
                                        }
                                        else ->{
                                            resources.getString(R.string.course_category_beginner)
                                        }
                                    }
                                    accordionDataList.add(
                                        CourseAccordionData(
                                            accordionTittle = categoryName,
                                            accordionListAdapter = coursesAdapter
                                        )
                                    )
                                }
                                CoroutineScope(Dispatchers.Main).launch {
                                    with(binding) {
                                        customAccordionList.setCousesData(data = accordionDataList)
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

    }
}