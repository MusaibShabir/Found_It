package com.example.foundit.presentation.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.foundit.presentation.data.local.dao.ProfileDataDao
import com.example.foundit.presentation.data.local.tables.ProfileData

@Database(entities = [ProfileData::class], version = 2, exportSchema = false)
abstract class FoundItDatabase : RoomDatabase() {
    abstract fun foundItDao(): ProfileDataDao

    companion object {
         val MIGRATION_1_2 = Migration(1, 2) {
             it.execSQL("ALTER TABLE ProfileData ADD COLUMN totalFound INTEGER NOT NULL DEFAULT 0")
             it.execSQL("ALTER TABLE ProfileData ADD COLUMN totalReported INTEGER NOT NULL DEFAULT 0")
             it.execSQL("ALTER TABLE ProfileData ADD COLUMN memberSince TEXT NOT NULL DEFAULT '0000-00-00'")
         }
    }


}