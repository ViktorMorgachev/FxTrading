package com.fx_trading.lessons.feature_common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseUtil @Inject constructor(val firebaseStorage: FirebaseStorage, val firebaseFirestore: FirebaseFirestore) {

    private val storageRef by lazy {
        firebaseStorage.reference
    }

    private fun getReferenceFromFirebaseUrl(firebaseUrl: String): StorageReference{
        return firebaseStorage.getReferenceFromUrl(firebaseUrl)
    }

    public fun loadImage(imageView: ImageView, context: Context, firebaseUrl: String){
        Glide.with(context)
            .load(getReferenceFromFirebaseUrl(firebaseUrl)).addListener(requestListener)
            .into(imageView)
    }

}