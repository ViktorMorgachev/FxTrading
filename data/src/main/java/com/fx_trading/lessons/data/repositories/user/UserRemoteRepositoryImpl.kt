package com.fx_trading.lessons.data.repositories.user

import com.fx_trading.lessons.data.api.user.ApiUser
import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(var firebaseDatabase: FirebaseDatabase): UserRemoteRepository {


    override suspend fun fetchUserByUserID(userID: String): ApiUser {
        TODO("Not yet implemented")
    }

    override suspend fun checkUserByDeviceIDInDatabase(deviceID: String): Boolean {
        val myRef: DatabaseReference = firebaseDatabase.getReference("usersDeviceID")
        myRef.get().addOnFailureListener {
            Logger.log("UserRemoteRepository", exception = it)
        }.addOnCompleteListener {
            Logger.log("UserRemoteRepository", message = "Data ${it.result}")
        }
        return false
    }

    override suspend fun updateUserData(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserIDByDeviceID(deviceID: String): String? {
        TODO("Not yet implemented")
    }
}