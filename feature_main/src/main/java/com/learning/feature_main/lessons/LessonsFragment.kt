package com.learning.feature_main.lessons

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.learning.common.State
import com.learning.feature_main.custom.LessonAccordionData
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.entities.lesson.hasCategory
import com.learning.lessons.feature_main.ui.main.MainFragmentDirections
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentLessonsBinding
import com.learning.navigation.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LessonsFragment : BaseFragment<FragmentLessonsBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<LessonsViewModel>

    private val viewModel: LessonsViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @Inject
    lateinit var router: Router

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLessonsBinding =
        FragmentLessonsBinding::inflate

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.getData().collect { state ->
                when (state) {
                    is State.LoadingState -> {

                    }
                    is State.ErrorState -> {

                    }
                    is State.DataState -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            val allLessons = state.data.first

                            val categories = mutableListOf<String>()
                            allLessons.map { it.categories }.forEach { allCategories ->
                                allCategories.forEach { category->
                                    if (!categories.contains(category))
                                        categories.add(category)
                                }
                            }
                            val accordionDataList = mutableListOf<LessonAccordionData>()

                            categories.forEach { category ->
                                val actualLessons = mutableListOf<Lesson>()
                                allLessons.forEach { lesson->
                                    if (lesson.hasCategory(category)) {
                                        actualLessons.add(lesson)
                                    }
                                }
                                val lessonAdapter = LessonsAdapter(
                                    data = actualLessons,
                                    openLessonAction = { lesson->
                                        findNavController(this@LessonsFragment).navigate(MainFragmentDirections.actionMainFragmentToLessonFragment(lessonId = lesson.id))
                                    },
                                    likeLessonAction = {
                                        viewModel.likeLesson(it)
                                    },
                                    completedLessonIDs = state.data.second,
                                )
                                accordionDataList.add(LessonAccordionData(
                                    accordionTittle = category,
                                    accordionListAdapter = lessonAdapter
                                ))
                            }
                            if (categories.isEmpty()){
                                val lessonAdapter = LessonsAdapter(
                                    data = allLessons,
                                    openLessonAction = { lesson->
                                        findNavController(this@LessonsFragment).navigate(MainFragmentDirections.actionMainFragmentToLessonFragment(lessonId = lesson.id))
                                    },
                                    likeLessonAction = {
                                        viewModel.likeLesson(it)
                                    },
                                    completedLessonIDs = state.data.second,
                                )
                                accordionDataList.add(LessonAccordionData(
                                    accordionTittle = resources.getString(R.string.all_lessons),
                                    accordionListAdapter = lessonAdapter
                                ))
                            }
                            CoroutineScope(Dispatchers.Main).launch {
                                with(binding) {
                                    customAccordionList.setLessonsData(data = accordionDataList)
                                }
                            }
                        }
                    }
                }
            }
            viewModel.likedLesson.observe(viewLifecycleOwner) { lesson->
                lesson?.let {
                    with(binding){
                        LessonsAdapter.actualLessons.add(lesson)
                        customAccordionList.updateData<LessonsAdapter, Lesson>(lesson)
                    }
                }
            }
        }

    }
}