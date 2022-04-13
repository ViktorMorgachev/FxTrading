package com.learning.feature_main.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.learning.common.State
import com.learning.common.setDifficulty
import com.learning.feature_main.lessons.LessonsAdapter
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentCourseBinding
import com.learning.lessons.utils.utils.gone
import com.learning.lessons.utils.utils.visible
import com.learning.navigation.Router
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourseFragment : BaseFragment<FragmentCourseBinding>() {

    private var courseID: Int = -1

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
        courseID = arguments?.getInt("course_id") ?: -1
        with(binding) {
            recyclerLessons.layoutManager = LinearLayoutManager(requireContext())
            toolbar.cancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        lifecycleScope.launch { 
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.getCourse(courseID).collect { state->
                        when(state){
                            is State.DataState ->{
                                showCourse(state.data)
                            }
                        }
                    }
                }
            }
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                    viewModel.subscribeToCourses().collect { courses ->
                        showCourse(courses.firstOrNull { it.id == courseID } to viewModel.passedLessons)
                    }
                }
            }
           
        }

    }

    private fun showCourse(data: Pair<Course?, List<Int>>) {
        with(binding){
            val course = data.first ?: return
            val completedLessons = data.second
            tvCourseTitle.text = course.title

            Glide.with(courseImageView.context)
                .load(course.promo_image_url).error(R.drawable.mock_video_image)
                .into(courseImageView)

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
                                NavHostFragment.findNavController(this@CourseFragment).navigate(CourseFragmentDirections.actionCourseFragmentToLessonFragment(lessonId = lesson.id, lessonOrder = course.lessons_id.indexOf(lesson.id)))
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
                                recyclerLessons.adapter = LessonsAdapter(data = sortedlessons, openLessonAction = openLessonAction, likeLessonAction = likeLessonAction, completedLessonIDs = completedLessons, fromCourse = true)
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