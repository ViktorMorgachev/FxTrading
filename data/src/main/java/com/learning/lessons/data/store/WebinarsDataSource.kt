package com.learning.lessons.data.store

import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.mappers.toApiWebinar
import com.learning.lessons.data.mappers.toWebinar
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.WebinarRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsDataSource @Inject constructor(val webinarsRemoteRepository: WebinarsRemoteRepository): RepositoryCacheable<ApiWebinar> {

     suspend fun getWebinars(): List<Webinar> {
        return webinarsRemoteRepository.getWebinars().map { it.toWebinar() }
    }

     suspend fun getWebinarByID(id: Int): Webinar? {
      return webinarsRemoteRepository.getWebinarByID(id)?.toWebinar()
    }

     suspend fun updateWebinarField(
        webinarID: Int,
        fieldValue: Any,
        field: String
    ): Deferred<Boolean> = withContext(Dispatchers.IO) {
        return@withContext async {webinarsRemoteRepository.updateWebinarField(webinarID, fieldValue, field).last() }
    }

}