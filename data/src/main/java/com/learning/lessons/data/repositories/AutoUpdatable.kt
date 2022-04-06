package com.learning.lessons.data.repositories

interface AutoUpdatable {
    suspend fun subscribeToChangesCollection(updateAction: ()->Unit)
    suspend fun subscribeToChangeDocument(documentID: Int, updateAction: ()->Unit)
}