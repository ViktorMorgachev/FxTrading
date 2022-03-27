package com.learning.lessons.android.di.module

import com.learning.lessons.data.repositories.lessons.*
import com.learning.lessons.data.repositories.question.QuestionProvider
import com.learning.lessons.data.repositories.question.QuestionRemoteRepository
import com.learning.lessons.data.repositories.question.QuestionRemoteRepositoryImpl
import com.learning.lessons.data.repositories.user.UserRemoteRepository
import com.learning.lessons.data.repositories.user.UserRemoteRepositoryImpl
import com.learning.lessons.data.repositories.user.UsersProvider
import com.learning.lessons.data.repositories.webinars.WebinarsProvider
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepositoryImpl
import com.learning.lessons.domain.repositories.LessonRepository
import com.learning.lessons.domain.repositories.QuestionRepository
import com.learning.lessons.domain.repositories.UserRepository
import com.learning.lessons.domain.repositories.WebinarRepository
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