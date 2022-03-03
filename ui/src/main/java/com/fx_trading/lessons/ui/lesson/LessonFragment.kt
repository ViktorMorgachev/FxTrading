package com.fx_trading.lessons.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.ui.R
import com.fx_trading.lessons.ui.databinding.FragmentLessonBinding
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