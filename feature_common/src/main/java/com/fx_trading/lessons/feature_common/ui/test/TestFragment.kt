package com.fx_trading.lessons.feature_common.ui.test

import com.fx_trading.lessons.feature_common.R
import com.google.firebase.firestore.FirebaseFirestore
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class TestFragment : MvpAppCompatFragment(R.layout.fragment_test) {

    @Inject
    lateinit var firestore: FirebaseFirestore

    override fun onResume() {
        super.onResume()
        firestore.app.name
    }
}