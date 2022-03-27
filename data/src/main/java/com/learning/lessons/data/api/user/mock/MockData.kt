package com.learning.lessons.data.api.user.mock

import android.os.Build
import androidx.annotation.RequiresApi
import com.learning.lessons.data.api.user.ApiUser
import kotlinx.datetime.Clock

class MockData {
    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        val defaultUser = ApiUser(date_created = "${Clock.System.now()}", date_logined = "${Clock.System.now()}", email = "", language = "", name = "", rank = 0, region = "", user_id = -1, utm = "")
    }
}