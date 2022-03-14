package com.fx_trading.lessons.features.ui.lesson

import com.fx_trading.lessons.domain.entities.lesson.Lesson

interface LessonView {
    fun showLesson(lesson: Lesson)
}