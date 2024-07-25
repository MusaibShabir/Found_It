package com.example.foundit

import android.app.Application
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

        val dummyProfile = ProfileData(firstName = "Musaib", lastName = "Shabir", countryCode = 91, id = 73)
        GlobalScope.launch {
            profileRepository.upsertProfile(dummyProfile)
        }

    }

}