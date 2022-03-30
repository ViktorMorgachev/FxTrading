package com.learning.lessons.data.repositories.lessons

import com.google.firebase.firestore.FieldValue
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.lesson.ApiLesson
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.api.question_group.ApiQuestionGroup
import com.learning.lessons.data.extentions.toObjectOrDefault
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.asDeferred
import javax.inject.Inject

class LessonsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): LessonsRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun getRemoteLessons(): List<ApiLesson> {
        return try {
            val firebaseDocuments =  firebaseFirestore.collection("${documentPath}Lessons").get().await()
            firebaseDocuments?.mapNotNull { it.toObjectOrDefault(ApiLesson::class.java) }?.filter { it.active } ?: listOf()
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            listOf()
        }
    }

    override suspend fun getRemoteLessonByID(id: Int): ApiLesson? {
        return try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}Lessons").document("$id").get().await()
            firebaseDocument?.toObjectOrDefault(ApiLesson::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun updateLessonField(lessonID: Int, fieldValue: Any, field: String) = flow<Boolean> {
        try {
            val firebaseDocumentRef = firebaseFirestore.collection("${documentPath}Lessons").document("$lessonID")
            if (fieldValue is  Array<*>) {
                firebaseDocumentRef.update(field, FieldValue.arrayUnion(fieldValue)).addOnSuccessListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(true)
                    }
                }.addOnFailureListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }.addOnFailureListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }
            }
            else {
                firebaseDocumentRef.update(mapOf(field to fieldValue)).addOnSuccessListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(true)
                    }
                }.addOnFailureListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }.addOnCanceledListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }
            }
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            emit(false)
        }
    }

}