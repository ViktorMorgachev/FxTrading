package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.repositories.lessons.GetLessons
import javax.inject.Inject


class GetLessonsUseCase @Inject constructor(private val getLessons: GetLessons){

}