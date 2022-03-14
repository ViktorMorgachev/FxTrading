package com.fx_trading.lessons.data.api.user.mock

import android.os.Build
import androidx.annotation.RequiresApi
import com.fx_trading.lessons.data.api.user.ApiUser
import java.time.LocalDateTime

class MockData {
    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        val defaultUser = ApiUser(date_created = "${LocalDateTime.now()}", date_logined = "${LocalDateTime.now()}", email = "", language = "", name = "", rank = 0, region = "", user_id = -1, utm = "")
    }
}