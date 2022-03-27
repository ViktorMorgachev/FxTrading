package com.learning.lessons.features.ui.lesson

import com.learning.lessons.domain.entities.lesson.Lesson

interface LessonView {
    fun showLesson(lesson: Lesson)
}