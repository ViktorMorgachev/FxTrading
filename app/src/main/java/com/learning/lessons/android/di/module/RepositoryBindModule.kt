package com.learning.lessons.android.di.module

import com.learning.lessons.data.repositories.courses.CourseProvider
import com.learning.lessons.data.repositories.courses.CourseRemoteRepository
import com.learning.lessons.data.repositories.courses.CourseRemoteRepositoryImpl
import com.learning.lessons.data.repositories.lessons.*
import com.learning.lessons.data.repositories.question.QuestionProvider
import com.learning.lessons.data.repositories.question.QuestionRemoteRepository
import com.learning.lessons.data.repositories.question.QuestionRemoteRepositoryImpl
import com.learning.lessons.data.repositories.user.UserRemoteRepository
import com.learning.lessons.data.repositories.user.UserRemoteRepositoryImpl
import com.learning.lessons.data.repositories.user.UsersProvider
import com.learning.lessons.data.repositories.userInfo.UserInfoProvider
import com.learning.lessons.data.repositories.userInfo.UserInfoRemoteRepository
import com.learning.lessons.data.repositories.userInfo.UserInfoRemoteRepositoryImpl
import com.learning.lessons.data.repositories.webinars.WebinarsProvider
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepositoryImpl
import com.learning.lessons.domain.repositories.*
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBindModule() {

    @Binds
    abstract fun bindLessonRemoteRepository(lessonsRemoteRepositoryImpl: LessonsRemoteRepositoryImpl): LessonsRemoteRepository

    @Binds
    abstract fun bindLessonBaseRepository(lessonProvider: LessonProvider): LessonRepository

    @Binds
    abstract fun bindWebinarsRemoteRepository(webinarsRemoteRepositoryImpl: WebinarsRemoteRepositoryImpl): WebinarsRemoteRepository
    @Binds
    abstract fun bindWebinarsBaseRepository(webinarsProvider: WebinarsProvider): WebinarRepository


    @Binds
    abstract fun bindQuestionsRemoteRepository(questionRemoteRepositoryImpl: QuestionRemoteRepositoryImpl): QuestionRemoteRepository
    @Binds
    abstract fun bindQuestionBaseRepository(questionProvider: QuestionProvider): QuestionRepository

    @Binds
    abstract fun bindUserRemoteRepository(userRemoteRepositoryImpl: UserRemoteRepositoryImpl): UserRemoteRepository

    @Binds
    abstract fun bindUserBaseRepository(usersProvider: UsersProvider): UserRepository


    @Binds
    abstract fun bindUserInfoRemoteRepository(userInfoRemoteRepositoryImpl: UserInfoRemoteRepositoryImpl): UserInfoRemoteRepository
    @Binds
    abstract fun bindUserInfoBaseRepository(userInfoProvider: UserInfoProvider): UserInfoRepository

    @Binds
    abstract fun bindCourseRemoteRepository(courseRemoteRepositoryImpl: CourseRemoteRepositoryImpl): CourseRemoteRepository

    @Binds
    abstract fun bindCourseBaseRepository(courseProvider: CourseProvider): CourseRepository



}