package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.repositories.lessons.LessonRepository
import javax.inject.Inject


class LessonsUseCase @Inject constructor(private val lessonRepository: LessonRepository){

}