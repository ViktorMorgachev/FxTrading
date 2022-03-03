package com.fx_trading.lessons.ui.lesson

import com.fx_trading.lessons.domain.entities.lesson.Lesson
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LessonFragment : MvpAppCompatFragment(), LessonView {

    @InjectPresenter
    lateinit var lessonPresenter: LessonPresenter

    override fun showLesson(lesson: Lesson) {

    }

    override fun onResume() {
        super.onResume()
        lessonPresenter.getLesson(1)
    }
}