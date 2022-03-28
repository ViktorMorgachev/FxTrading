package com.learning.lessons.data.store

import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.mappers.toApiWebinar
import com.learning.lessons.data.mappers.toWebinar
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.WebinarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsDataSource @Inject constructor(val webinarsRemoteRepository: WebinarsRemoteRepository) : WebinarRepository, RepositoryCacheable<ApiWebinar> {

    override suspend fun getWebinars(): List<Webinar> {
        return webinarsRemoteRepository.getWebinars().map { it.toWebinar() }
    }

    override suspend fun getWebinarByID(id: Int): Webinar? {
      return webinarsRemoteRepository.getWebinarByID(id)?.toWebinar()
    }

    override suspend fun updateWebinarField(
        webinarID: Int,
        fieldValue: Any,
        field: String
    ): Boolean {
       return webinarsRemoteRepository.updateWebinarField(webinarID, fieldValue, field)
    }

}