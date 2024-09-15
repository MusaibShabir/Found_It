package com.example.foundit.presentation.screens.actions

import android.widget.Toast
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
import androidx.compose.material3.MenuAnchorType.Companion.PrimaryNotEditable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.addCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionComponent(
    modifier: Modifier,
    navController: NavHostController
) {
    val viewModel: ActionComponentViewModel = hiltViewModel()

    val context = LocalContext.current

    var selectedPhone by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }


    var phoneExpanded by remember { mutableStateOf(false) }
    var modelExpanded by remember { mutableStateOf(false) }
    var colorExpanded by remember { mutableStateOf(false) }


    val phoneOptions = listOf("Phone 1", "Phone 2", "Phone 3")
    val modelOptions = listOf("Model A", "Model B", "Model C")
    val colorOptions = listOf("Red", "Green", "Blue")

    var locationEntered by remember { mutableStateOf("") }
    var discriptionEntered by remember { mutableStateOf("") }

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
                    .menuAnchor(type = PrimaryNotEditable, enabled = true)
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
                    .menuAnchor(type = PrimaryNotEditable, enabled = true)
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
                    .menuAnchor(type = PrimaryNotEditable, enabled = true)
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

        //Item Location
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            value = locationEntered,
            onValueChange = { locationEntered = it },
            label = { Text("Location") },
            placeholder = { Text("Enter Location", fontStyle = FontStyle.Italic) },
            shape = MaterialTheme.shapes.medium,
            maxLines = 1,
        )

        //Item Description
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            value = discriptionEntered,
            onValueChange = { discriptionEntered = it },
            label = { Text("Description") },
            placeholder = { Text("Enter Description", fontStyle = FontStyle.Italic) },
            shape = MaterialTheme.shapes.medium,
            maxLines = 3,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {navController.popBackStack()}) {
                Text(text = "Cancel")
            }

            Button(
                onClick = {
                    // TODO("to be reviewed")
                    addCard(
                        cardColorCode = 0,
                        itemTitle = selectedPhone,
                        itemDescription = discriptionEntered,
                        itemLocation = locationEntered,
                        progressIndicator = true
                    )
                    viewModel.sendData(
                        selectedPhone, selectedModel, selectedColor
                    ) { isSuccess ->
                        if (isSuccess) {
                            Toast.makeText(context, "item added!", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "error!", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                enabled = selectedPhone.isNotEmpty()
                        && selectedModel.isNotEmpty()
                        && selectedColor.isNotEmpty()
            ) {
                Text(text = "Submit")
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
    ActionScreen(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current)
    )
}


