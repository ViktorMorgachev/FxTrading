package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonProvider
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.data.repositories.question.QuestionProvider
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepository
import com.fx_trading.lessons.data.store.LessonsDataSource
import com.fx_trading.lessons.data.store.QuestionsDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryProvidesModule() {

    @Provides
    @Singleton
    fun provideLessonProvider(lessonsRemoteRepository: LessonsRemoteRepository): LessonProvider {
        return LessonProvider(LessonsDataSource(lessonsRemoteRepository))
    }

    @Provides
    @Singleton
    fun provideQuestionProvider(questionRemoteRepository: QuestionRemoteRepository): QuestionProvider {
        return QuestionProvider(QuestionsDataSource(questionRemoteRepository))
    }

}