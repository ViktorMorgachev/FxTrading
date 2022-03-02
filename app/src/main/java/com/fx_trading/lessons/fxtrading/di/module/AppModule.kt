package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.data.repositories.lessons.LessonRepositoryImpl
import com.fx_trading.lessons.domain.repositories.lessons.LessonRepository
import com.fx_trading.lessons.domain.usecases.LessonsUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    RepositoryBindModule::class,
    RepositoryProvidesModule::class])
class AppModule() {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}