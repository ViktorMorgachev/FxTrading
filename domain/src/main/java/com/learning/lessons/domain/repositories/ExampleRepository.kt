package com.learning.lessons.domain.repositories

interface ExampleRepository {
    suspend fun getData(): List<Any>
}