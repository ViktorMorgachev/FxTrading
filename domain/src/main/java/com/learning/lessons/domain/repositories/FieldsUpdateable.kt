package com.learning.lessons.domain.repositories

import kotlinx.coroutines.Deferred

interface FieldsUpdateable {
    suspend fun updateFields(objectID: Int, fieldValue: List<Pair<String, Any>>): Deferred<Boolean>
}