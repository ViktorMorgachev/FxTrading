package com.learning.lessons.data.repositories

import kotlinx.coroutines.flow.Flow
interface FieldsUpdateable {
    suspend fun updateFields(objectID: Int, fieldValues:  List<Pair<String, Any>>): Flow<Boolean>
}