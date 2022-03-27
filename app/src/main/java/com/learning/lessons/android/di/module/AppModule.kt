package com.learning.lessons.android.di.module

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import data.DataStoreHelper
import javax.inject.Singleton

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
    @Singleton
    fun provideDataStoreHelper(context: Context): DataStoreHelper {
        return DataStoreHelper(context)
    }

}