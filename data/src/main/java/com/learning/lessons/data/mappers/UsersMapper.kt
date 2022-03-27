package com.learning.lessons.data.mappers

import com.learning.lessons.data.api.user.ApiUser
import com.learning.lessons.domain.entities.user.User

fun ApiUser.toUser(): User{
    return User(date_created =date_created, date_logined = date_logined, email = email, language = language, name = name, rank= rank, region = region,  user_id = user_id, utm = utm)
}

fun User.toApiUser(): ApiUser{
    return ApiUser(date_created = date_created, date_logined = date_logined, email = email, language = language, name = name, rank= rank, region = region,  user_id = user_id, utm = utm)
}