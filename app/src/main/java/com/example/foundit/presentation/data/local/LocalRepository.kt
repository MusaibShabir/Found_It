package com.example.foundit.presentation.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.foundit.presentation.data.local.dao.ProfileDataDao
import com.example.foundit.presentation.data.local.tables.ProfileData

@Database(entities = [ProfileData::class], version = 3, exportSchema = false)
abstract class FoundItDatabase : RoomDatabase() {
    abstract fun foundItDao(): ProfileDataDao

    companion object {

        //  New Columns Added [totalFound, totalReported, memberSince]
        val MIGRATION_1_2 = Migration(1, 2) {
             it.execSQL("ALTER TABLE ProfileData ADD COLUMN totalFound INTEGER NOT NULL DEFAULT 0")
             it.execSQL("ALTER TABLE ProfileData ADD COLUMN totalReported INTEGER NOT NULL DEFAULT 0")
             it.execSQL("ALTER TABLE ProfileData ADD COLUMN memberSince TEXT NOT NULL DEFAULT '0000-00-00'")
         }

        // Profile_Id Data Type modified from Int -> Long
        val MIGRATION_2_3 = Migration(2, 3) {
            it.execSQL(
                """
            CREATE TABLE IF NOT EXISTS `new_profile_data` (
                `firstName` TEXT NOT NULL,
                `lastName` TEXT NOT NULL,
                `countryCode` INTEGER NOT NULL,
                `totalFound` INTEGER NOT NULL,
                `totalReported` INTEGER NOT NULL,
                `memberSince` TEXT NOT NULL,
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL
            );
            """
            )

            it.execSQL(
                """
            INSERT INTO `new_profile_data` (`firstName`, `lastName`, `countryCode`, `totalFound`, `totalReported`, `memberSince`, `id`)
            SELECT `firstName`, `lastName`, `countryCode`, `totalFound`, `totalReported`, `memberSince`, CAST(`id` AS LONG) 
            FROM `ProfileData`;
            """
            )


            it.execSQL("DROP TABLE IF EXISTS `ProfileData`;")

            it.execSQL("ALTER TABLE `new_profile_data` RENAME TO `ProfileData`;")
        }


    }


}