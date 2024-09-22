package com.example.foundit.presentation.screens.input.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foundit.presentation.screens.input.lost.LostInputViewModel
import com.example.foundit.ui.theme.MainGreen

@Composable
fun ItemDescriptionScreen(
    modifier: Modifier,
    viewModel: LostInputViewModel
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            elevation = CardDefaults.elevatedCardElevation(36.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Add Item Description",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(500)
                )

                Spacer(modifier = modifier.height(24.dp))

                val itemDescription by viewModel.itemDescription.collectAsState()

                val maxChar = 250

                OutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
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
                        cursorColor = MainGreen,
                        focusedIndicatorColor = MainGreen
                    )

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