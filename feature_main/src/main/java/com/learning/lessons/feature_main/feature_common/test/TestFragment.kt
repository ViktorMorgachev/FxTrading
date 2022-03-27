package com.learning.lessons.feature_main.feature_common.test

import android.view.LayoutInflater
import android.view.ViewGroup
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.features.databinding.FragmentTestBinding
import com.google.firebase.firestore.FirebaseFirestore
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