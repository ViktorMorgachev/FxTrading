package com.fx_trading.lessons.fxtrading.di.module

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module(includes = [
    RepositoryBindModule::class,
    RepositoryProvidesModule::class])
class AppModule() {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}