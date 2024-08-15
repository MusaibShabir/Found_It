package com.example.foundit.di

import com.example.foundit.presentation.data.account.AccountService
import com.example.foundit.presentation.data.account.AccountServiceImpl
import com.google.firebase.auth.FirebaseAuth
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
}