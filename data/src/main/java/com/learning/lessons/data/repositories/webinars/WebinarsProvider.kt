package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.mappers.toWebinar
import com.learning.lessons.data.store.WebinarsDataSource
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.WebinarRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsProvider  @Inject constructor(private val webinarsDataSource: WebinarsDataSource): WebinarRepository {

    override suspend fun getWebinars(): List<Webinar> {
       return webinarsDataSource.getWebinars().map { it.toWebinar() }
    }

    override suspend fun getWebinarByID(id: Int): Webinar? {
        return webinarsDataSource.getWebinarByID(id)?.toWebinar()
    }

    override suspend fun getWebinarForceByID(id: Int): Webinar? {
        return webinarsDataSource.getWebinarByID(id, forceUpdate = true)?.toWebinar()
    }

    override suspend fun updateWebinar(webinar: Webinar): Boolean {
        return webinarsDataSource.updateWebinar(webinar)
    }
}