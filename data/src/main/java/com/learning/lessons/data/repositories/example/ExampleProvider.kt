package com.learning.lessons.data.repositories.example

import com.learning.lessons.data.store.ExampleDataSource
import com.learning.lessons.domain.repositories.ExampleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleProvider @Inject constructor(private val exampleDataSource: ExampleDataSource): ExampleRepository {
    override suspend fun getData(): List<Any> {
     return  exampleDataSource.getExampleData()
    }
}