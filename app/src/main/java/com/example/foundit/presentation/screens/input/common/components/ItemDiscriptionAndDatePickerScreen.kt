package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel
import com.example.foundit.ui.theme.MainGreen

@Composable
fun ItemDescriptionAndDatePickerScreen(
    modifier: Modifier,
    viewModel: LostInputViewModel
) {


    val cardType by viewModel.cardType.collectAsState()

    var descriptionCategoryTopHeading by remember { mutableStateOf("") }
    when(cardType){
        0 -> descriptionCategoryTopHeading = "Provide a brief description of your lost item to assist in its quick recovery."
        1 -> descriptionCategoryTopHeading = "Provide a brief description of your found item to assist in its quick recovery."
    }

    var datePickerTopHeading by remember { mutableStateOf("") }
    when(cardType){
        0 -> datePickerTopHeading = "Tell us the date when the item was lost."
        1 -> datePickerTopHeading = "Tell us the date when you found the item."
    }

    var datePickerTitle by remember { mutableStateOf("") }
    when(cardType){
        0 -> datePickerTitle = "Select the date when the item was lost"
        1 -> datePickerTitle = "Select the date when the item was found"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            OutlinedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MainGreen)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = descriptionCategoryTopHeading,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                }
            }

        }
        HorizontalDivider(modifier = modifier.padding(vertical = 10.dp))

        OutlinedCard(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(vertical = 18.dp),
            colors = CardDefaults.cardColors(containerColor = MainGreen.copy(alpha = .25f)),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ){


                Spacer(modifier = modifier.height(24.dp))

                val itemDescription by viewModel.itemDescription.collectAsState()

                val maxChar = 250


                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    value = itemDescription,
                    onValueChange = {
                        if (it.length <= maxChar) {
                            viewModel.updateItemDescription(it)
                        }
                    },
                    supportingText = {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ){
                            when(itemDescription.length){
                                maxChar -> Text(
                                    text = "Description Max Limit Reached",
                                    color = Color.Red
                                )
                                else -> return@OutlinedTextField
                            }
                        }

                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                    ),


                    placeholder = {
                        Text(
                            text = "Your Item Description which will help the user to find it",
                            fontStyle = FontStyle.Italic
                        )
                    },
                    maxLines = 5,
                    trailingIcon = {
                        IconButton(
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear Button"
                                )
                            },
                            onClick = { viewModel.updateItemDescription( "") },
                            enabled = itemDescription.isNotEmpty()
                        )
                    },

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        cursorColor = MainGreen,
                        focusedIndicatorColor = MainGreen
                    )

                )
            }
        }
        Spacer(modifier.height(18.dp))

        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            OutlinedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MainGreen)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = datePickerTopHeading,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                }
            }

        }
        HorizontalDivider(modifier = modifier.padding(vertical = 10.dp))
        OutlinedCard(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(vertical = 18.dp),
            colors = CardDefaults.cardColors(containerColor = MainGreen.copy(alpha = .25f)),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ){
                DatePickerDocked(
                    modifier = modifier,
                    datePickerTitle = datePickerTitle,
                    viewModel = viewModel
                )

            }
        }

    }

}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewItemDescriptionScreen() {
    ItemDescriptionScreen(
        modifier = Modifier,
        viewModel = LostInputViewModel()
    )

}

 */