package com.learning.lessons.data.repositories.example

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleRemoteDataImpl @Inject constructor(): ExampleRemoteRepository {
    override fun getRemoteData(): List<Any> {
        TODO("Not yet implemented")
    }
}