package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonRepositoryImpl
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.domain.repositories.lessons.LessonRepository
import com.fx_trading.lessons.domain.usecases.LessonsUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryProvidesModule() {

    @Provides
    @Singleton
    fun provideLessonRepositoryImpl(lessonsRemoteRepository: LessonsRemoteRepository): LessonRepository{
        return LessonRepositoryImpl(lessonsRemoteRepository)
    }
}