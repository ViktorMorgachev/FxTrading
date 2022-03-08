package com.fx_trading.lessons.fxtrading.di.module

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class AppModule() {

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    fun provideTheme(context: Context): Resources.Theme = context.theme

    @Provides
    fun provideContentResolver(context: Context): ContentResolver =
        context.contentResolver

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}