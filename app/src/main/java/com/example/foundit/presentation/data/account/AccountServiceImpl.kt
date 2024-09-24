package com.example.foundit.presentation.data.account

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AccountService {

    // provides Id of current firebase user
    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    // provides account creation date of current firebase user
    override val accountCreationDate: Long?
        get() = firebaseAuth.currentUser?.metadata?.creationTimestamp

    // provides Name of current firebase user
    override val currentUserName: String
        get() = firebaseAuth.currentUser?.displayName ?: "User"

    // provides Email of current firebase user
    override val currentUserEmail: String
        get() = firebaseAuth.currentUser?.email.orEmpty()

    // provides Profile photo of current firebase user
    override val currentUserPhotoUrl: Uri?
        get() = firebaseAuth.currentUser?.photoUrl

    // checks whether the current user email is verified or not : returns Boolean value
    override val currentUserEmailVerified: Boolean
        get() = firebaseAuth.currentUser?.isEmailVerified ?: false

    // provides provider infomation of current firebase user
    override val currentUserProviderData: MutableList<out UserInfo>?
        get() = firebaseAuth.currentUser?.providerData

    // checks whether current user object is empty or not for handling session : returns Boolean value
    override fun hasUser(): Boolean {
        return firebaseAuth.currentUser != null
    }

    // it is used to reload current user object
    override suspend fun refreshCurrentUser() {
        firebaseAuth.currentUser?.reload()?.await()
    }

    // it is used to create account with custom email and password : it takes email and password as input
    override suspend fun createAccount(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).await()
    }

    // it is used to login user with custom email and password : it takes email and password as input
    override suspend fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password).await()
    }

    // it is used to sign-up or sign-in user with Google : it takes idToken string as input
    override suspend fun signInWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(firebaseCredential).await()
    }

    // it is used to send verification email
    override suspend fun sendEmailVerification() {
        firebaseAuth.currentUser?.sendEmailVerification()?.await()
    }

    // it is used to update user name
    override suspend fun update(firstName: String, lastName: String) {
        val profileUpdates = userProfileChangeRequest {
            displayName = "$firstName  $lastName"
        }
        firebaseAuth.currentUser?.updateProfile(profileUpdates)?.await()
    }

    // it is used to update Profile
    override suspend fun updateProfile(firstName: String, lastName: String, profilePicture: Uri?) {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val profileUpdates = userProfileChangeRequest {
                displayName = "$firstName $lastName"  // Single space between first and last name
                // Todo profile photo to be implemented
                //photoUri = Uri.parse(profilePicture.toString())  // Can be null; Firebase will handle it
            }
            currentUser.updateProfile(profileUpdates).await()
        } else {
            throw IllegalStateException("No authenticated user found.")
        }
    }

    // it is used to update email
    override suspend fun updateEmail(email: String) {
        firebaseAuth.currentUser?.verifyBeforeUpdateEmail(email)?.await()
    }

    // it is used to update password
    override suspend fun updatePassword(password: String) {
        firebaseAuth.currentUser?.updatePassword(password)
    }

    // it is used to logout current user
    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    // it is used to delete account of current user
    override suspend fun deleteAccount() {
        firebaseAuth.currentUser?.delete()?.await()
    }
}