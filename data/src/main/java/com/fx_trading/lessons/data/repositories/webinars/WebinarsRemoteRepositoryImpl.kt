package com.fx_trading.lessons.data.repositories.webinars

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.webinar.ApiWebinar
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class WebinarsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : WebinarsRemoteRepository {

    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun getWebinars(): List<ApiWebinar> {
        TODO("Not yet implemented")
    }

    override suspend fun getWebinarByID(id: Long): ApiWebinar {
        TODO("Not yet implemented")
    }

    override suspend fun updateWebinar(webinar: ApiWebinar) {
        TODO("Not yet implemented")
    }
}