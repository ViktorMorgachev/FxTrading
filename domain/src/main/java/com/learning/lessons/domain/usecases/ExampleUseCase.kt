package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.repositories.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleUseCase @Inject constructor(private val exampleRepository: ExampleRepository) {

    suspend fun getData(): List<Any>{
        return exampleRepository.getData()
    }
}