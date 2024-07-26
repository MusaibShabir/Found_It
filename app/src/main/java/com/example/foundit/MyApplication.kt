package com.example.foundit

import android.app.Application

import com.example.foundit.presentation.data.local.repo.ProfileDataRepository
import com.example.foundit.presentation.data.local.tables.ProfileData
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var profileRepository: ProfileDataRepository

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()

        val dummyProfile = ProfileData(
            firstName = "Musaib",
            lastName = "Shabir",
            countryCode = 91,
            totalFound = 10,
            totalReported = 3,
            id = 73
        )
        GlobalScope.launch {
            profileRepository.upsertProfile(dummyProfile)
        }

    }

}