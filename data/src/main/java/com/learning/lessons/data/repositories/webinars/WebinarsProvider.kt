package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.store.WebinarsDataSource
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.WebinarRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsProvider @Inject constructor(private val webinarsDataSource: WebinarsDataSource){

    suspend fun getWebinars(): List<Webinar> {
        return webinarsDataSource.getWebinars()
    }

    suspend fun getWebinarByID(id: Int): Webinar? {
        return webinarsDataSource.getWebinarByID(id)
    }

    suspend fun updateWebinarField(
        webinarID: Int,
        fieldValue: Any,
        field: String
    ): Deferred<Boolean> {
        return webinarsDataSource.updateWebinarField(webinarID, fieldValue, field)
    }
}