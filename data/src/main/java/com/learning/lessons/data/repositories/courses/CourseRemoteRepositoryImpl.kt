package com.learning.lessons.data.repositories.courses

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.api.course.ApiCourse
import com.learning.lessons.data.extentions.await
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): CourseRemoteRepository {
    private val logger_tag = this::class.java.simpleName
    private val documentPath = "dev"

    override suspend fun getCourses(): List<ApiCourse> {
        return try {
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}Courses").get().await()
            firebaseDocuments?.documents?.mapNotNull { it.toObjectOrDefault(ApiCourse::class.java) }?.filter { it.active }?: listOf()
        } catch (e : Exception){
            Logger.log(logger_tag,  exception = e)
            listOf()
        }
    }

    override suspend fun getCourseByID(id: Int): ApiCourse? {
        return try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}Courses").document("$id").get().await()
            firebaseDocument?.toObjectOrDefault(ApiCourse::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun updateCourseField(
        courseID: Int,
        fieldValue: Any,
        field: String
    ) = flow {
        try {
            val firebaseDocumentRef = firebaseFirestore.collection("${documentPath}Courses").document("$courseID")
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