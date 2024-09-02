package com.example.foundit.presentation.screens.documentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foundit.R
import com.example.foundit.presentation.common.TheTopAppBar

@Composable
fun PrivacyPolicyScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TheTopAppBar(title = "Privacy Policy", navController = navController)
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
                text = stringResource(id = R.string.privacy_policy_date),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Welcome Message
            Text(
                text = stringResource(id = R.string.privacy_policy_welcome),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Information We Collect
            Text(
                text = stringResource(id = R.string.privacy_policy_info_collect),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_info_provide),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_account_info),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_user_content),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_auto_collect),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_device_info),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_location_data),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_usage_data),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_third_party),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_social_media),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // How We Use Your Information
            Text(
                text = stringResource(id = R.string.privacy_policy_use_info),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_provide_improve),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_communicate),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_personalize),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_security_fraud),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // How We Share Your Information
            Text(
                text = stringResource(id = R.string.privacy_policy_share_info),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_consent),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_service_providers),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_legal_compliance),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_business_transfers),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Your Choices
            Text(
                text = stringResource(id = R.string.privacy_policy_your_choices),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_access_correction),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_location_tracking),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_opt_out),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Security
            Text(
                text = stringResource(id = R.string.privacy_policy_security),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_security_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Children's Privacy
            Text(
                text = stringResource(id = R.string.privacy_policy_children_privacy),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_children_privacy_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // International Users
            Text(
                text = stringResource(id = R.string.privacy_policy_international_users),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_international_users_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Changes to This Privacy Policy
            Text(
                text = stringResource(id = R.string.privacy_policy_changes),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_changes_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Contact Us
            Text(
                text = stringResource(id = R.string.privacy_policy_contact),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_contact_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.privacy_policy_contact_details),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Thank You
            Text(
                text = stringResource(id = R.string.privacy_policy_thank_you),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyScreenPreview() {
    PrivacyPolicyScreen(navController = rememberNavController())
}