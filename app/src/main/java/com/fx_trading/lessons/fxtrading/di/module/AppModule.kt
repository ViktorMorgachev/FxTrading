package com.fx_trading.lessons.fxtrading.di.module

import com.fx_trading.lessons.fxtrading.di.AppScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class AppModule() {

    @Provides
    @AppScope
    fun provideFirebaseFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}