package com.example.foundit.presentation.data.local.repo

import com.example.foundit.presentation.data.local.dao.ProfileDataDao
import com.example.foundit.presentation.data.local.tables.ProfileData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProfileDataRepository @Inject constructor(private val profileDataDao: ProfileDataDao) {

    suspend fun upsertProfile(profileData: ProfileData){
        profileDataDao.upsertProfile(profileData)
    }

    fun getProfileData(): Flow<ProfileData>{
        return profileDataDao.getProfileData()
    }

}
