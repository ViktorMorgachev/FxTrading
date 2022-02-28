package com.fx_trading.lessons.domain.interactors

import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.usecases.GetLessonsUseCase
import javax.inject.Inject

class GetLessonsInteractor @Inject constructor(): GetLessonsUseCase {

    override fun fetchLessons(): List<Lesson> {
        TODO("Not yet implemented")
    }

}