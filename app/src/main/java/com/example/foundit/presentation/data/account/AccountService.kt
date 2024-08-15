package com.example.foundit.presentation.data.account

interface AccountService {
        val currentUserId: String
        fun hasUser(): Boolean
        suspend fun createAccount(email:String, password:String)
        suspend fun login(email: String, password: String)
        suspend fun sendEmailVerification()
        suspend fun logout()
        suspend fun signInWithGoogle(idToken: String)
        suspend fun deleteAccount()
}