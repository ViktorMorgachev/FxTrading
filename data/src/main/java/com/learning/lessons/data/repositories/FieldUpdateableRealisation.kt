package com.learning.lessons.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.extentions.await
import com.learning.lessons.data.extentions.containsAll
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FieldUpdateableRealisation @Inject constructor (private val firebaseFirestore: FirebaseFirestore): FieldsUpdateable {

     var updateFieldDocumentPath: String = ""

     override suspend fun updateFields(
        objectID: Int,
        fieldValues: List<Pair<String, Any>>
    )= flow {
        try {
            val firebaseDocumentRef =
                firebaseFirestore.collection(updateFieldDocumentPath).document("$objectID")
            fieldValues.forEach { fieldValue ->
                firebaseDocumentRef.update(mapOf(fieldValue.first to fieldValue.second)).await()
            }
            val firebaseDocument =
                firebaseFirestore.collection(updateFieldDocumentPath).document("$objectID").get().await()
            emit(firebaseDocument!!.containsAll(fieldValues))
        } catch (e: Exception) {
            Logger.log("FieldUpdateableImpl", exception = e)
            emit(false)
        }
    }
}