package com.example.foundit.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.foundit.presentation.data.local.FoundItDatabase
import com.example.foundit.presentation.data.local.FoundItDatabase.Companion.MIGRATION_1_2
import com.example.foundit.presentation.data.local.FoundItDatabase.Companion.MIGRATION_2_3
import com.example.foundit.presentation.data.local.dao.ProfileDataDao
import com.example.foundit.presentation.data.local.repo.ProfileDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): FoundItDatabase {
        return Room.databaseBuilder(
            context,
            FoundItDatabase::class.java,
            "Local Database"

        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    }

    @Provides
    @Singleton
    fun provideProfileDataDao(db: FoundItDatabase): ProfileDataDao = db.foundItDao()

    @Provides
    @Singleton
    fun provideProfileDataRepository(dao: ProfileDataDao): ProfileDataRepository = ProfileDataRepository(profileDataDao = dao)

}