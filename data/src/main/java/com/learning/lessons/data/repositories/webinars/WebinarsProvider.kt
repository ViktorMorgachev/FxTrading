package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.store.WebinarsDataSource
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.WebinarRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsProvider @Inject constructor(private val webinarsDataSource: WebinarsDataSource): WebinarRepository{

    override suspend fun getWebinars(): List<Webinar> {
        return webinarsDataSource.getWebinars()
    }

    override suspend fun getWebinarByID(id: Int): Webinar? {
        return webinarsDataSource.getWebinarByID(id)
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> {
        return webinarsDataSource.updateWebinarFields(objectID, fieldValues = fieldValue)
    }
}