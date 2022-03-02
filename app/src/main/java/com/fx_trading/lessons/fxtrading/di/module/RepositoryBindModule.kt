package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonRepositoryImpl
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepositoryImpl
import com.fx_trading.lessons.domain.repositories.lessons.LessonRepository
import com.fx_trading.lessons.domain.usecases.LessonsUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryBindModule() {

    @Binds
    abstract fun bindLessonRemoteRepository(lessonsRemoteRepositoryImpl: LessonsRemoteRepositoryImpl): LessonsRemoteRepository
    @Binds
    abstract fun bindLessonRepository(lessonRepositoryImpl: LessonRepositoryImpl): LessonRepository
}