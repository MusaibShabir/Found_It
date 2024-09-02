package com.example.foundit.presentation.screens.documentation

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
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foundit.R
import com.example.foundit.presentation.common.TheTopAppBar


@Composable
fun TermsOfServiceScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Terms of Service", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Effective Date
            Text(
                text = stringResource(id = R.string.terms_of_service_date),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Welcome Message
            Text(
                text = stringResource(id = R.string.terms_of_service_welcome),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Acceptance of Terms
            Text(
                text = stringResource(id = R.string.terms_of_service_acceptance),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_acceptance_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Eligibility
            Text(
                text = stringResource(id = R.string.terms_of_service_eligibility),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_eligibility_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // How Found It Works
            Text(
                text = stringResource(id = R.string.terms_of_service_how_it_works),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_data_collection),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_matching_system),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_notifications),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // User Responsibilities
            Text(
                text = stringResource(id = R.string.terms_of_service_user_responsibilities),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_accuracy),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_privacy),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_compliance),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Data Collection and Privacy
            Text(
                text = stringResource(id = R.string.terms_of_service_data_privacy),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_data_collected),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_data_usage),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_data_security),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Intellectual Property
            Text(
                text = stringResource(id = R.string.terms_of_service_intellectual_property),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_intellectual_property_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Limitation of Liability
            Text(
                text = stringResource(id = R.string.terms_of_service_limitation_of_liability),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_limitation_of_liability_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Termination
            Text(
                text = stringResource(id = R.string.terms_of_service_termination),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_termination_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Changes to the Terms
            Text(
                text = stringResource(id = R.string.terms_of_service_changes),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_changes_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Governing Law
            Text(
                text = stringResource(id = R.string.terms_of_service_governing_law),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_governing_law_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Contact Information
            Text(
                text = stringResource(id = R.string.terms_of_service_contact_information),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.terms_of_service_contact_information_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Thank You
            Text(
                text = stringResource(id = R.string.terms_of_service_thank_you),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TermsOfServiceScreenPreview() {
    TermsOfServiceScreen(navController = rememberNavController())
}