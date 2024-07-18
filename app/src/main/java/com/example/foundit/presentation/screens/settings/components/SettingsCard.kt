package com.example.foundit.presentation.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foundit.presentation.data.settingsOptionList

@Composable
fun SettingsOption(
    settingsOptionName: String,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable { },
    ) {
        Text(
            text = settingsOptionName,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
            contentDescription = null
        )
    }
}

@Composable
fun SettingsCard(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
    ) {
        for (option in settingsOptionList)
            SettingsOption(option)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsCardPreview(){
    SettingsCard()
}