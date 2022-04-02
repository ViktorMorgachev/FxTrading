package com.learning.lessons.feature_main.ui.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.common.State
import com.learning.common.setDifficulty
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.feature_main.ui.lessons.LessonsAdapter
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentCourseBinding
import com.learning.lessons.utils.utils.gone
import com.learning.lessons.utils.utils.visible
import com.learning.navigation.Router
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CourseFragment : BaseFragment<FragmentCourseBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<CourseViewModel>

    private val viewModel: CourseViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @Inject
    lateinit var router: Router
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCourseBinding = FragmentCourseBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val courseID = arguments?.getInt("course_id") ?: -1
        with(binding) {
            recyclerLessons.layoutManager = LinearLayoutManager(requireContext())
            toolbar.cancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getCourse(courseID).collect { state->
                when(state){
                    is State.DataState ->{
                        showCourse(state.data)
                    }
                }
            }
        }

    }

    private fun showCourse(data: Pair<Course, List<Int>>) {
        with(binding){
            val course = data.first
            val completedLessons = data.second
            tvCourseTitle.text = course.title
            tvCountOfLessons.text = resources.getString(R.string.count_of_lessons, course.lessons_id.size)
            likeDislikeItem.likeItem.tvLikeText.text = "${course.likes}"
            difficultyName.setDifficulty(course.difficulty)
            difficultyItem.setDifficulty(course.difficulty)
            description.text = course.description
            lifecycleScope.launchWhenResumed {
                viewModel.getCourseLessons(course.lessons_id).collect { state ->
                    when(state){
                        is State.DataState ->{

                            val likeLessonAction: (Int)->Unit = { lessonID->
                                lifecycleScope.launchWhenCreated {
                                    viewModel.likeLesson(lessonID = lessonID).collect { state ->
                                        when (state) {
                                            is State.DataState -> {
                                                LessonsAdapter.actualLessons.add(state.data)
                                            }
                                        }
                                    }
                                }
                            }
                            val openLessonAction: (Lesson)->Unit = { lesson->
                                NavHostFragment.findNavController(this@CourseFragment).navigate(CourseFragmentDirections.actionCourseFragmentToLessonFragment(lessonId = lesson.id))
                            }
                            val sortedlessons = mutableListOf<Lesson>()

                            course.lessons_id.forEach { lessonId->
                                sortedlessons.add(state.data.first { it.id == lessonId })
                            }

                            val dataForList = if (sortedlessons.size > 3){
                                showMoreButton.visible()
                                sortedlessons.take(3)
                            } else {
                                showMoreButton.gone()
                                sortedlessons
                            }


                            showMoreButton.setOnClickListener {
                                recyclerLessons.adapter = LessonsAdapter(data = sortedlessons, openLessonAction = openLessonAction, likeLessonAction = likeLessonAction, completedLessonIDs = completedLessons)
                                showMoreButton.gone()
                            }

                            recyclerLessons.adapter = LessonsAdapter(data = dataForList, openLessonAction = openLessonAction, likeLessonAction = likeLessonAction, completedLessonIDs = completedLessons, fromCourse = true)
                        }
                    }
                }
            }
        }
    }

}