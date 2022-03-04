package com.fx_trading.lessons.feature_main.ui.lesson

import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.feature_main.R
import com.fx_trading.lessons.feature_main.databinding.FragmentLessonBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LessonFragment : MvpAppCompatFragment(R.layout.fragment_lesson), LessonView {

    @InjectPresenter
    lateinit var lessonPresenter: LessonPresenter

    private val binding : FragmentLessonBinding by viewBinding(FragmentLessonBinding::bind)

    override fun showLesson(lesson: Lesson) {

    }

    override fun onResume() {
        super.onResume()
        lessonPresenter.getLesson(1)
    }
}