package com.example.foundit.presentation.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class ProfileData(

    val firstName: String,
    val lastName: String,
    val countryCode: Int,
    val totalFound: Int,
    val totalReported: Int,
    val memberSince: LocalDate = LocalDate.now(),

    @PrimaryKey(autoGenerate = true)
    val id: Int,
)




