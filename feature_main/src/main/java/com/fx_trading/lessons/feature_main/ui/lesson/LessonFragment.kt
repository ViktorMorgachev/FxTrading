package com.fx_trading.lessons.feature_main.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.features.databinding.FragmentLessonBinding
import com.fx_trading.lessons.features.ui.lesson.LessonView
import com.fx_trading.navigation.Router
import javax.inject.Inject

class LessonFragment : BaseFragment<FragmentLessonBinding>(), LessonView {

    @Inject
    lateinit var router: Router

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLessonBinding = FragmentLessonBinding::inflate

    override fun showLesson(lesson: Lesson) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            lifecycle.addObserver(youtubePlayerView)
        }
    }
}