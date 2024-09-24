package com.example.foundit.presentation.data.account

import android.net.Uri
import com.google.firebase.auth.UserInfo

interface AccountService {
        val currentUserId: String
        val accountCreationDate: Long?
        val currentUserName: String
        val currentUserEmail: String
        val currentUserProviderData: MutableList<out UserInfo>?
        val currentUserPhotoUrl: Uri?
        val currentUserEmailVerified: Boolean
        fun hasUser(): Boolean
        suspend fun refreshCurrentUser()
        suspend fun createAccount(email:String, password:String)
        suspend fun login(email: String, password: String)
        suspend fun sendEmailVerification()
        suspend fun update(firstName: String, lastName: String)
        suspend fun updateProfile(firstName: String, lastName: String, profilePicture: Uri?)
        suspend fun updateEmail(email: String)
        suspend fun updatePassword(password: String)
        suspend fun logout()
        suspend fun signInWithGoogle(idToken: String)
        suspend fun deleteAccount()
}