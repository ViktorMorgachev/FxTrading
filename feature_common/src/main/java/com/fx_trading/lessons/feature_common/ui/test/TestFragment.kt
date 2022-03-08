package com.fx_trading.lessons.feature_common.ui.test

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_common.R
import com.fx_trading.lessons.feature_common.databinding.FragmentTestBinding
import com.google.firebase.firestore.FirebaseFirestore
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class TestFragment : BaseFragment<FragmentTestBinding>()  {

    @Inject
    lateinit var firestore: FirebaseFirestore

    override fun onResume() {
        super.onResume()
    }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTestBinding
        get() = FragmentTestBinding::inflate
}