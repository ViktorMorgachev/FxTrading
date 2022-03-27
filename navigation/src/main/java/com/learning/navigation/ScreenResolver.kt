package com.learning.navigation

import androidx.navigation.NavController
import com.learning.navigation.params.screens.ScreenParams

interface ScreenResolver {

    fun navigate(
        navController: NavController?,
        data: ScreenParams,
        sharedElements: Map<Any, String>?
    )

}