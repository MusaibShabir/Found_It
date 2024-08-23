package com.example.foundit.presentation.screens.actions

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var selectedPhone by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }


    var phoneExpanded by remember { mutableStateOf(false) }
    var modelExpanded by remember { mutableStateOf(false) }
    var colorExpanded by remember { mutableStateOf(false) }


    val phoneOptions = listOf("Phone 1", "Phone 2", "Phone 3")
    val modelOptions = listOf("Model A", "Model B", "Model C")
    val colorOptions = listOf("Red", "Green", "Blue")

    Column(
        modifier = modifier
            .padding(14.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = phoneExpanded,
            onExpandedChange = { phoneExpanded = !phoneExpanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedPhone,
                onValueChange = {},
                label = { Text("Phone") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = phoneExpanded) },
                placeholder = { Text("Select Phone", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .padding(bottom = 18.dp)
            )

            ExposedDropdownMenu(
                expanded = phoneExpanded,
                onDismissRequest = { phoneExpanded = false }
            ) {
                phoneOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedPhone = selectionOption
                            phoneExpanded = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = modelExpanded,
            onExpandedChange = { modelExpanded = !modelExpanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedModel,
                onValueChange = {},
                label = { Text("Model") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = modelExpanded) },
                placeholder = { Text("Select Model", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .padding(bottom = 18.dp)
            )

            ExposedDropdownMenu(

                expanded = modelExpanded,
                onDismissRequest = { modelExpanded = false }
            ) {
                modelOptions.forEach { options ->
                    DropdownMenuItem(
                        text = { Text(options) },
                        onClick = {
                            selectedModel = options
                            modelExpanded = false
                        }
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = colorExpanded,
            onExpandedChange = { colorExpanded = !colorExpanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedColor,
                onValueChange = {},
                label = { Text("Color") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = colorExpanded) },
                placeholder = { Text("Select Color", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .padding(bottom = 18.dp)
            )

            ExposedDropdownMenu(

                expanded = colorExpanded,
                onDismissRequest = { colorExpanded = false }
            ) {
                colorOptions.forEach { option3 ->
                    DropdownMenuItem(
                        text = { Text(option3) },
                        onClick = {
                            selectedColor = option3
                            colorExpanded = false
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { }) {
                Text(text = "Submit")
            }

            Button(onClick = {
                navController
                    .popBackStack()
                    .toString()
            }) {
                Text(text = "Cancel")
            }
        }
    }
}

@Composable
fun ActionScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar ={
            TheTopAppBar(
                title = "Action Screen",
                navController = navController,
                backRoute = navController
                    .popBackStack()
                    .toString()
            )
        }
    ) {innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding),
        ) {
            ActionComponent(modifier,navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewActionScreen() {
    ActionScreen(modifier = Modifier, navController = NavHostController(LocalContext.current))
}


