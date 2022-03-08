package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonProvider
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepositoryImpl
import com.fx_trading.lessons.data.repositories.question.QuestionProvider
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepository
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepositoryImpl
import com.fx_trading.lessons.domain.repositories.LessonRepository
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBindModule() {

    @Binds
    abstract fun bindLessonRemoteRepository(lessonsRemoteRepositoryImpl: LessonsRemoteRepositoryImpl): LessonsRemoteRepository
    @Binds
    abstract fun bindQuestionsRemoteRepository(questionRemoteRepositoryImpl: QuestionRemoteRepositoryImpl): QuestionRemoteRepository

    @Binds
    abstract fun bindLessonRepository(lessonProvider: LessonProvider): LessonRepository

    @Binds
    abstract fun bindQuestionRepository(questionProvider: QuestionProvider): QuestionRepository

}