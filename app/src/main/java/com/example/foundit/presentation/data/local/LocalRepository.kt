package com.example.foundit.presentation.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foundit.presentation.data.local.dao.ProfileDataDao
import com.example.foundit.presentation.data.local.tables.ProfileData

@Database(entities = [ProfileData::class], version = 1, exportSchema = false)
abstract class FoundItDatabase : RoomDatabase() {
    abstract fun foundItDao(): ProfileDataDao

}