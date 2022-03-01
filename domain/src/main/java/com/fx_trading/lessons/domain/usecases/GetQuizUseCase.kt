package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.quiz.Quiz

interface GetQuizUseCase {
    suspend fun fetchQuizUseCase(): List<Quiz>
}