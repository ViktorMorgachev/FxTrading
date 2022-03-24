package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.repositories.lessons.*
import com.fx_trading.lessons.data.repositories.question.QuestionProvider
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepository
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepositoryImpl
import com.fx_trading.lessons.data.repositories.user.UserRemoteRepository
import com.fx_trading.lessons.data.repositories.user.UserRemoteRepositoryImpl
import com.fx_trading.lessons.data.repositories.user.UsersProvider
import com.fx_trading.lessons.data.repositories.webinars.WebinarsProvider
import com.fx_trading.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.fx_trading.lessons.data.repositories.webinars.WebinarsRemoteRepositoryImpl
import com.fx_trading.lessons.domain.repositories.LessonRepository
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import com.fx_trading.lessons.domain.repositories.UserRepository
import com.fx_trading.lessons.domain.repositories.WebinarRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBindModule() {

    @Binds
    abstract fun bindLessonRemoteRepository(lessonsRemoteRepositoryImpl: LessonsRemoteRepositoryImpl): LessonsRemoteRepository

    @Binds
    abstract fun bindWebinarsRemoteRepository(webinarsRemoteRepositoryImpl: WebinarsRemoteRepositoryImpl): WebinarsRemoteRepository

    @Binds
    abstract fun bindLessonMockRepository(lessonsMockRepositoryImpl: LessonsMockRepositoryImpl): LessonsMockRepository

    @Binds
    abstract fun bindLessonBaseRepository(lessonProvider: LessonProvider): LessonRepository

    @Binds
    abstract fun bindWebinarsBaseRepository(webinarsProvider: WebinarsProvider): WebinarRepository


    @Binds
    abstract fun bindQuestionsRemoteRepository(questionRemoteRepositoryImpl: QuestionRemoteRepositoryImpl): QuestionRemoteRepository

    @Binds
    abstract fun bindUserRemoteRepository(userRemoteRepositoryImpl: UserRemoteRepositoryImpl): UserRemoteRepository



    @Binds
    abstract fun bindQuestionRepository(questionProvider: QuestionProvider): QuestionRepository

    @Binds
    abstract fun bindUserRepository(usersProvider: UsersProvider): UserRepository

}