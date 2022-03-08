package com.fx_trading.navigation

import androidx.fragment.app.Fragment
import com.fx_trading.navigation.params.screens.ScreenParams

interface ScreenFactory {

    fun getFragment(data: ScreenParams): Fragment?

}