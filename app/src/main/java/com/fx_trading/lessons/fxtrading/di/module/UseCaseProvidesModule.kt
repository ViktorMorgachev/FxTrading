package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.domain.repositories.QuestionRepository
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseProvidesModule {

    @Provides
    @Singleton
    fun provideQuestionsUseCase(questionRepository: QuestionRepository): QuestionUseCase {
        return QuestionUseCase(questionRepository)
    }

}