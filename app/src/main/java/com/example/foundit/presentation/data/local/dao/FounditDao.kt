package com.example.foundit.presentation.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.foundit.presentation.data.local.tables.ProfileData
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDataDao {

    @Upsert
    suspend fun upsertProfile(profileData: ProfileData)

    @Query("UPDATE ProfileData SET firstName = :firstName, lastName = :lastName WHERE id = :id")
    suspend fun updateProfileData(id: Long, firstName: String, lastName: String)

    @Query("SELECT * FROM ProfileData")
    fun getProfileData(): Flow<ProfileData>
}
