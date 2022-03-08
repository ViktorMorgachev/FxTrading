package com.fx_trading.navigation

import androidx.navigation.NavController
import com.fx_trading.navigation.params.screens.ScreenParams

interface ScreenResolver {

    fun navigate(
        navController: NavController?,
        data: ScreenParams,
        sharedElements: Map<Any, String>?
    )

}