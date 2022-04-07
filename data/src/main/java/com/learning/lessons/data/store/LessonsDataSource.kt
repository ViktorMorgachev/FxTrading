package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toLesson
import com.learning.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.last
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonsDataSource @Inject constructor(
    private val lessonsRemoteRepository: LessonsRemoteRepository
) {

    private val lessons: MutableStateFlow<List<Lesson>> = MutableStateFlow(listOf())
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        subscribe()
    }

    private fun subscribe() {
        val update = {
            Logger.log("LessonDataSource", "update.invoke()")
            coroutineScope.launch {
                lessons.emit(lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() })
            }
        }
        coroutineScope.launch {
            lessons.emit(lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() })
            lessonsRemoteRepository.subscribeToChangesCollection() {
                update.invoke()
            }
            lessons.value.forEach {
                lessonsRemoteRepository.subscribeToChangeDocument(it.id) {
                    update.invoke()
                }
            }
        }
    }

    suspend fun getLessonsFlow(): MutableStateFlow<List<Lesson>> {
        return lessons
    }

    suspend fun getLessons(): List<Lesson> {
        return if (lessons.value.isNotEmpty()) lessons.value else
            lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }
    }

    suspend fun getLessonByID(lessonID: Int): Lesson? {
        return  lessons.value.firstOrNull { it.id == lessonID } ?: lessonsRemoteRepository.getRemoteLessonByID(lessonID)?.toLesson()
    }

    suspend fun getLessonsByTags(tags: List<String>): List<Lesson> {
        return if (lessons.value.isNotEmpty()) lessons.value else
            lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }
    }

    suspend fun getLessonsByIDS(lessonsIDS: List<Int>): List<Lesson> {
        val lessons = if (lessons.value.isNotEmpty()) lessons.value.toMutableList() else lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }.toMutableList()
        lessons.removeAll { !lessonsIDS.contains(it.id) }
        return lessons
    }

    suspend fun updateLessonFields(
        lessonID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Deferred<Boolean> = withContext(Dispatchers.IO) {
        return@withContext async {
            lessonsRemoteRepository.updateFields(lessonID, fieldValues).last()
        }
    }

}