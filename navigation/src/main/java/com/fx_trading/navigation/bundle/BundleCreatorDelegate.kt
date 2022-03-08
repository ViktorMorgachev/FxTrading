package com.fx_trading.navigation.bundle

import android.os.Bundle
import com.fx_trading.navigation.params.screens.ScreenParams

inline fun <T : ScreenParams> bundleCreateDelegate(
    crossinline bundleCreator: ((T) -> Bundle),
) = object : BundleCreator() {

    @Suppress("UNCHECKED_CAST")
    override fun createBundle(data: Any): Bundle {
        return bundleCreator(data as T)
    }

}