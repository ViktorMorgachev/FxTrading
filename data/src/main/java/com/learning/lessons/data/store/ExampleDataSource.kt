package com.learning.lessons.data.store


import com.learning.lessons.data.repositories.example.ExampleRemoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleDataSource @Inject constructor(private val exampleRemoteRepository: ExampleRemoteRepository) {
    suspend fun getExampleData(): List<Any> {
       return exampleRemoteRepository.getRemoteData()
    }
}