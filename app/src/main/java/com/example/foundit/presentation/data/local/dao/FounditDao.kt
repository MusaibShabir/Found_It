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

    @Query("SELECT * FROM ProfileData WHERE id = :id")
    fun getProfileById(id: Int): Flow<ProfileData>
}
