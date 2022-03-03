package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonRepositoryImpl
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.data.repositories.quiz.QuestionRemoteRepository
import com.fx_trading.lessons.data.repositories.quiz.QuestionRepositoryImpl
import com.fx_trading.lessons.domain.repositories.LessonRepository
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryProvidesModule() {

    @Provides
    @Singleton
    fun provideLessonRepositoryImpl(lessonsRemoteRepository: LessonsRemoteRepository): LessonRepository {
        return LessonRepositoryImpl(lessonsRemoteRepository)
    }

    @Provides
    @Singleton
    fun provideQuestionRepositoryImpl(questionRemoteRepository: QuestionRemoteRepository): QuestionRepository {
        return QuestionRepositoryImpl(questionRemoteRepository)
    }
}