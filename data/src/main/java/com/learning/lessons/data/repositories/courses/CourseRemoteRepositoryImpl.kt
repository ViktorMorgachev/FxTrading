package com.learning.lessons.data.repositories.courses

import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.course.ApiCourse
import com.learning.lessons.data.extentions.await
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.data.repositories.AutoUpdatableRealiztion
import com.learning.lessons.data.repositories.FieldUpdateableRealisation
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore, private val autoUpdatableRealiztion: AutoUpdatableRealiztion): CourseRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = "${BuildConfig.DOCUMENT_DB_PATH}Courses"

    private val fieldUpdateableRealisation: FieldUpdateableRealisation by lazy {
        FieldUpdateableRealisation(firebaseFirestore)
    }


    init {
        fieldUpdateableRealisation.updateFieldDocumentPath = documentPath
        autoUpdatableRealiztion.documentPath = documentPath
    }

    override suspend fun getCourses(): List<ApiCourse> {
        return try {
            val firebaseDocuments = firebaseFirestore.collection(documentPath).get().await()
            firebaseDocuments?.documents?.mapNotNull { it.toObjectOrDefault(ApiCourse::class.java) }?.filter { it.active }?: listOf()
        } catch (e : Exception){
            Logger.log(logger_tag,  exception = e)
            listOf()
        }
    }

    override suspend fun getCourseByID(id: Int): ApiCourse? {
        return try {
            val firebaseDocument = firebaseFirestore.collection(documentPath).document("$id").get().await()
            firebaseDocument?.toObjectOrDefault(ApiCourse::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Flow<Boolean> {
        return fieldUpdateableRealisation.updateFields(objectID, fieldValues)
    }

    override suspend fun subscribeToChangesCollection(updateAction: () -> Unit) {
       autoUpdatableRealiztion.subscribeToChangesCollection(updateAction)
    }

    override suspend fun subscribeToChangeDocument(documentID: Int, updateAction: () -> Unit) {
       autoUpdatableRealiztion.subscribeToChangeDocument(documentID, updateAction)
    }


}