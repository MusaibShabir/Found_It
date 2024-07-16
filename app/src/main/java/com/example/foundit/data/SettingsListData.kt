package com.example.foundit.data

data class SettingsListData(
    val settingsOptionList: List<String>
)

val settingsOptionList: List<String> = listOf(
    "Account Information",
    "Language",
    "Appearance",
    "Security",
    "Help and Support",
    "Feedback",
    "Share",
    "About"
)
