package com.fx_trading.lessons.feature_main.ui.lessons

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fx_trading.common.State
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.entities.lesson.hasCategory
import com.fx_trading.lessons.feature_main.ui.custom.LessonAccordionData
import com.fx_trading.lessons.features.databinding.FragmentLessonsBinding
import com.fx_trading.navigation.Router
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
            viewModel.getData().collect {
                when (it) {
                    is State.LoadingState -> {

                    }
                    is State.ErrorState -> {

                    }
                    is State.DataState -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            val allLessons = it.data
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
                                    openLessonAction = { lesson->{

                                    }},
                                    likeLessonAction = {
                                        viewModel.likeLesson(it)
                                    },
                                    completedLessonIDs = listOf(),
                                )
                                accordionDataList.add(LessonAccordionData(
                                    accordionTittle = category,
                                    accordionListAdapter = lessonAdapter
                                ))
                            }
                            CoroutineScope(Dispatchers.Main).launch {
                                with(binding) {
                                    customAccordionList.setData(data = accordionDataList)
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