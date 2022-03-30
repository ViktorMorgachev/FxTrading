package com.learning.lessons.android.di.module

import com.learning.lessons.data.repositories.lessons.LessonProvider
import com.learning.lessons.data.repositories.lessons.LessonsMockRepository
import com.learning.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.learning.lessons.data.repositories.question.QuestionProvider
import com.learning.lessons.data.repositories.question.QuestionRemoteRepository
import com.learning.lessons.data.repositories.user.UserRemoteRepository
import com.learning.lessons.data.repositories.user.UsersProvider
import com.learning.lessons.data.repositories.userInfo.UserInfoProvider
import com.learning.lessons.data.repositories.userInfo.UserInfoRemoteRepository
import com.learning.lessons.data.repositories.webinars.WebinarsProvider
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.learning.lessons.data.store.*
import com.learning.lessons.domain.repositories.UserInfoRepository
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

    @Provides
    @Singleton
    fun provideWebinarProvider(webinarsRemoteRepository: WebinarsRemoteRepository): WebinarsProvider {
        return WebinarsProvider(WebinarsDataSource(webinarsRemoteRepository))
    }

    @Provides
    @Singleton
    fun provideUsersProvider(userRemoteRepository: UserRemoteRepository): UsersProvider {
        return UsersProvider(UserDataSource(userRemoteRepository))
    }

    @Provides
    @Singleton
    fun provideUsersInfoProvider(userInfoRemoteRepository: UserInfoRemoteRepository): UserInfoProvider {
        return UserInfoProvider(UserInfoDataSource(userInfoRemoteRepository))
    }


}