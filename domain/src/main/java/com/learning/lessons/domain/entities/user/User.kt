package com.learning.lessons.domain.entities.user

data class User(
    val date_created: String,
    val date_logined: String,
    val email: String,
    val language: String,
    val name: String,
    val rank: Int,
    val region: String,
    val user_id: Int,
    val utm: String
)