package com.fx_trading.lessons.fxtrading.di.module

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import com.fx_trading.lessons.feature_common.FirebaseUtil
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
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

    @Singleton
    @Provides
    fun provideFirebaseFirestore(firebaseApp: FirebaseApp): FirebaseFirestore{
        return FirebaseFirestore.getInstance(firebaseApp)
    }

    @Singleton
    @Provides
    fun provideFirebaseApp(context: Context): FirebaseApp{
        FirebaseApp.initializeApp(context)
        return FirebaseApp.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(firebaseApp: FirebaseApp): FirebaseStorage {
        return FirebaseStorage.getInstance(firebaseApp)
    }
    @Singleton
    @Provides
    fun provideFirebaseUtils(firebaseFirestore: FirebaseFirestore, firebaseStorage: FirebaseStorage): FirebaseUtil{
        return FirebaseUtil(firebaseStorage, firebaseFirestore)
    }
}