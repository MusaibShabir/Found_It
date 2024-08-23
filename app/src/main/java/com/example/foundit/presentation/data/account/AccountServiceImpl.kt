package com.example.foundit.presentation.data.account

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AccountService {

    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override val currentUserName: String
        get() = firebaseAuth.currentUser?.displayName ?: "User"

    override val currentUserEmail: String
        get() = firebaseAuth.currentUser?.email.orEmpty()

    override val currentUserPhotoUrl: Uri?
        get() = firebaseAuth.currentUser?.photoUrl

    override val currentUserEmailVerified: Boolean
        get() = firebaseAuth.currentUser?.isEmailVerified ?: false

    override fun hasUser(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun refreshCurrentUser() {
        firebaseAuth.currentUser?.reload()?.await()
    }

    override suspend fun createAccount(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).await()
    }

    override suspend fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password).await()
    }

    override suspend fun signInWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(firebaseCredential).await()
    }

    override suspend fun sendEmailVerification() {
        firebaseAuth.currentUser?.sendEmailVerification()?.await()
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun deleteAccount() {
        firebaseAuth.currentUser?.delete()?.await()
    }
}

