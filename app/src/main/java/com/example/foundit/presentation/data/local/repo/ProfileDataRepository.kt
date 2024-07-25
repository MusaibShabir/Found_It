package com.example.foundit.presentation.data.local.repo

import com.example.foundit.presentation.data.local.dao.ProfileDataDao
import com.example.foundit.presentation.data.local.tables.ProfileData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProfileDataRepository @Inject constructor(private val profileDataDao: ProfileDataDao) {
    fun getProfileById(id: Int): Flow<ProfileData>{
        return profileDataDao.getProfileById(id)
    }

    suspend fun upsert(profileData: ProfileData){
        profileDataDao.upsertProfile(profileData)
    }

}
