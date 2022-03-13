package com.fx_trading.lessons.fxtrading.di.module

import android.content.Context
import com.fx_trading.lessons.feature_common.FirebaseUtil
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FireBaseModule {
    @Singleton
    @Provides
    fun provideFirebaseFirestore(firebaseApp: FirebaseApp): FirebaseFirestore {
        return FirebaseFirestore.getInstance(firebaseApp)
    }

    @Singleton
    @Provides
    fun provideFirebaseApp(context: Context): FirebaseApp {
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
    fun provideFirebaseUtils(firebaseFirestore: FirebaseFirestore, firebaseStorage: FirebaseStorage, firebaseDatabase: FirebaseDatabase): FirebaseUtil {
        return FirebaseUtil(firebaseStorage, firebaseFirestore, firebaseDatabase)
    }

    @Singleton
    @Provides
    fun provideFirebaseDatabase(firebaseApp: FirebaseApp): FirebaseDatabase{
        return FirebaseDatabase.getInstance(firebaseApp).also {
            it.setPersistenceEnabled(false)
        }
    }
}