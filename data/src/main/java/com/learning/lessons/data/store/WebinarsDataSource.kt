package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toWebinar
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.last
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsDataSource @Inject constructor(val webinarsRemoteRepository: WebinarsRemoteRepository) {


    private val webinars: MutableStateFlow<List<Webinar>> = MutableStateFlow(listOf())
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        subscribe()
    }

    private fun subscribe() {
        val update = {
            Logger.log("WebinarsDataSource", "update.invoke()")
            coroutineScope.launch {
                webinars.emit(webinarsRemoteRepository.getWebinars().map { it.toWebinar() })
            }
        }
        coroutineScope.launch {
            webinars.emit(webinarsRemoteRepository.getWebinars().map { it.toWebinar() })
            webinarsRemoteRepository.subscribeToChangesCollection() {
                update.invoke()
            }
            webinars.value.forEach {
                webinarsRemoteRepository.subscribeToChangeDocument(it.id) {
                    update.invoke()
                }
            }
        }
    }

    suspend fun getWebinarsFlow(): MutableStateFlow<List<Webinar>> {
        return webinars
    }

    suspend fun getWebinars(): List<Webinar> {
        return if(webinars.value.isNotEmpty()) webinars.value else
            webinarsRemoteRepository.getWebinars().map { it.toWebinar() }
    }

    suspend fun getWebinarByID(id: Int): Webinar? {
        return webinars.value.firstOrNull { it.id == id } ?: webinarsRemoteRepository.getWebinarByID(id)?.toWebinar()
    }

    suspend fun updateWebinarFields(
        webinarID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Deferred<Boolean> = withContext(Dispatchers.IO) {
        return@withContext async {
            webinarsRemoteRepository.updateFields(webinarID, fieldValues).last()
        }
    }

}