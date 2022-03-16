package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonProvider
import com.fx_trading.lessons.data.repositories.lessons.LessonsMockRepository
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.data.repositories.question.QuestionProvider
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepository
import com.fx_trading.lessons.data.repositories.user.UserRemoteRepository
import com.fx_trading.lessons.data.repositories.user.UsersProvider
import com.fx_trading.lessons.data.store.LessonsDataSource
import com.fx_trading.lessons.data.store.QuestionsDataSource
import com.fx_trading.lessons.data.store.UserDataSource
import com.fx_trading.lessons.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryProvidesModule() {

    // Это нормально, так и надо делать

    @Provides
    @Singleton
    fun provideLessonProvider(lessonsDataSource: LessonsDataSource): LessonProvider {
        return LessonProvider(lessonsDataSource)
    }

    @Provides
    @Singleton
    fun provideLessonDataSource(lessonsMockRepository: LessonsMockRepository, lessonsRemoteRepository: LessonsRemoteRepository): LessonsDataSource{
        return LessonsDataSource(lessonsRemoteRepository, lessonsMockRepository)
    }

    // TODO тут бы надо привести к правильному виду

    @Provides
    @Singleton
    fun provideQuestionProvider(questionRemoteRepository: QuestionRemoteRepository): QuestionProvider {
        return QuestionProvider(QuestionsDataSource(questionRemoteRepository))
    }

    @Provides
    @Singleton
    fun provideUsersProvider(userRemoteRepository: UserRemoteRepository): UsersProvider {
        return UsersProvider(UserDataSource(userRemoteRepository))
    }



}