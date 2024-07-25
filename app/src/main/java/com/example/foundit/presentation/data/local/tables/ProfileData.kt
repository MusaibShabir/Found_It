package com.example.foundit.presentation.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileData(

    val firstName: String,
    val lastName: String,
    val countryCode : Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int,
)




