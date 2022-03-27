package com.learning.lessons.android.di.module

import com.learning.lessons.data.repositories.lessons.LessonProvider
import com.learning.lessons.data.repositories.lessons.LessonsMockRepository
import com.learning.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.learning.lessons.data.repositories.question.QuestionProvider
import com.learning.lessons.data.repositories.question.QuestionRemoteRepository
import com.learning.lessons.data.repositories.user.UserRemoteRepository
import com.learning.lessons.data.repositories.user.UsersProvider
import com.learning.lessons.data.store.LessonsDataSource
import com.learning.lessons.data.store.QuestionsDataSource
import com.learning.lessons.data.store.UserDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryProvidesModule() {

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