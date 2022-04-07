package com.learning.lessons.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AutoUpdatableRealiztion @Inject constructor (private val firebaseFirestore: FirebaseFirestore): AutoUpdatable {

    var documentPath: String = ""

    override suspend fun subscribeToChangesCollection(updateAction: () -> Unit) {
        try {
            val collectionRef = firebaseFirestore.collection(documentPath)
            collectionRef.addSnapshotListener { snapshot, error ->
                CoroutineScope(Dispatchers.IO).launch {
                    if (error != null) {
                        Logger.log("AutoUpdatableRealiztion", exception = error)
                    }else{
                        Logger.log("AutoUpdatableRealiztion", "Collection was chandes")
                        updateAction.invoke()
                    }
                }
            }
        } catch (e: Exception) {
            Logger.log("AutoUpdatableRealiztion", exception = e)
        }
    }

    override suspend fun subscribeToChangeDocument(documentID: Int, updateAction: () -> Unit) {
        try {
            val documentRef = firebaseFirestore.collection(documentPath).document("$documentID")
            documentRef.addSnapshotListener { snapshot, error ->
                CoroutineScope(Dispatchers.IO).launch {
                    if (error != null) {
                        Logger.log("AutoUpdatableRealiztion", exception = error)
                    }else{
                        snapshot?.exists()?.let {
                            updateAction.invoke()
                        }
                        Logger.log("AutoUpdatableRealiztion", "Data was chanded")
                    }
                }
            }
        } catch (e: Exception) {
            Logger.log("AutoUpdatableRealiztion", exception = e)
        }
    }
}