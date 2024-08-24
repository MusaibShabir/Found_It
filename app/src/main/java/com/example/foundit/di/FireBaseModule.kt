package com.example.foundit.di

import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.data.account.AccountServiceImpl
import com.example.foundit.presentation.data.firestore.FirestoreService
import com.example.foundit.presentation.data.firestore.FirestoreServiceImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FireBaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAccountServiceImpl(firebaseAuth: FirebaseAuth): AccountService {
        return AccountServiceImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideFirestoreServiceImpl(firestore: FirebaseFirestore, accountService: AccountService): FirestoreService {
        return FirestoreServiceImpl(firestore, accountService)
    }
}