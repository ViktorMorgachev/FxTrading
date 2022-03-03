package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonRepositoryImpl
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepositoryImpl
import com.fx_trading.lessons.data.repositories.quiz.QuestionRemoteRepository
import com.fx_trading.lessons.data.repositories.quiz.QuestionRemoteRepositoryImpl
import com.fx_trading.lessons.data.repositories.quiz.QuestionRepositoryImpl
import com.fx_trading.lessons.domain.repositories.LessonRepository
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBindModule() {

    @Binds
    abstract fun bindLessonRemoteRepository(lessonsRemoteRepositoryImpl: LessonsRemoteRepositoryImpl): LessonsRemoteRepository
    @Binds
    abstract fun bindLessonRepository(lessonRepositoryImpl: LessonRepositoryImpl): LessonRepository

    @Binds
    abstract fun bindQuestionsRemoteRepository(questionRemoteRepositoryImpl: QuestionRemoteRepositoryImpl): QuestionRemoteRepository
    @Binds
    abstract fun bindQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl): QuestionRepository

}