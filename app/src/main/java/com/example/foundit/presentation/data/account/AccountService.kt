package com.example.foundit.presentation.data.account

import android.net.Uri

interface AccountService {
        val currentUserId: String
        val currentUserName: String
        val currentUserEmail: String
        val currentUserPhotoUrl: Uri?
        val currentUserEmailVerified: Boolean
        fun hasUser(): Boolean
        suspend fun refreshCurrentUser()
        suspend fun createAccount(email:String, password:String)
        suspend fun login(email: String, password: String)
        suspend fun sendEmailVerification()
        suspend fun logout()
        suspend fun signInWithGoogle(idToken: String)
        suspend fun deleteAccount()
}