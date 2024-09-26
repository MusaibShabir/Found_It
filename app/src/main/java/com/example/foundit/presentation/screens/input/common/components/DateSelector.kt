package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel
import com.example.foundit.ui.theme.MainGreen
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    modifier: Modifier,
    datePickerTitle: String,
    viewModel: LostInputViewModel
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val selectedDateString by viewModel.selectedDateString.collectAsState()
    val selectedDateMillis by viewModel.selectedDateMillis.collectAsState()

    val noFutureDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            val date = Instant.ofEpochMilli(utcTimeMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            return date <= LocalDate.now()
        }
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDateMillis,
        yearRange = 1922..2100,
        selectableDates = noFutureDates

    )


    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDateString,
            onValueChange = {},
            label = { Text("Date") },
            placeholder = { Text("Tap the button to select the date", fontStyle = FontStyle.Italic) },
            readOnly = true,
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp),
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date",
                        tint = MainGreen
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MainGreen,
            )
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    Button(
                        onClick = {
                            showDatePicker = false
                            datePickerState.selectedDateMillis?.let { millis ->
                                val selectedLocalDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                                viewModel.onDateSelected(selectedLocalDate)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MainGreen)
                    ) {
                        Text("Confirm")
                    }
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background,
                    todayDateBorderColor = MainGreen,
                    todayContentColor = MaterialTheme.colorScheme.onBackground
                ),
                content = {
                    DatePicker(
                        state = datePickerState,
                        title = { Text(
                            text = datePickerTitle,
                            modifier = modifier.padding(22.dp)
                        ) },
                        colors = DatePickerDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.background,
                            selectedDayContentColor = MaterialTheme.colorScheme.background,
                            selectedDayContainerColor = MainGreen,
                            selectedYearContainerColor = MainGreen,
                            dayInSelectionRangeContentColor = MainGreen,
                            todayDateBorderColor = MainGreen,
                            todayContentColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            )
        }
    }
}

