package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.repositories.LessonRepository
import com.learning.lessons.domain.repositories.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository, private val lessonRepository: LessonRepository) {




}