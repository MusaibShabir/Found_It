package com.example.foundit.presentation.screens.settings.components.clickable.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foundit.R
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun AcknowledgementScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Acknowledgements", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Acknowledgement Content
            Text(
                text = stringResource(id = R.string.acknowledgement_teacher),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AcknowledgementScreenPreview() {
    AcknowledgementScreen(navController = rememberNavController())
}