package com.fx_trading.lessons.feature_lesson.lesson

import com.fx_trading.lessons.domain.entities.lesson.Lesson
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LessonView: MvpView {
    fun showLesson(lesson: Lesson)
}