package com.fx_trading.navigation.bundle

import android.os.Bundle


abstract class BundleCreator {

    abstract fun createBundle(data: Any): Bundle

    companion object {

        fun empty() = object : BundleCreator() {
            override fun createBundle(data: Any): Bundle = Bundle.EMPTY
        }

    }

}