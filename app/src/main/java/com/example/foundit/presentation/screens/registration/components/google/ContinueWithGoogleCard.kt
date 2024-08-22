package com.example.foundit.presentation.screens.registration.components.google

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import com.example.foundit.R

@Composable
fun ContinueWithGoogleCard(
    modifier: Modifier,
    colorScheme: Int = 1,
    viewModel: ContinueWithGoogle,
    onGetCredentialResponse: (Credential) -> Unit
) {
    val context = LocalContext.current
    val credentialManager = CredentialManager.create(context)


    val containerColor = when(colorScheme) {
        1 -> Color.White
        2 -> Color.Blue
        else -> Color.White
    }
    val textColor = when (colorScheme) {
        1 -> Color.Blue
        2 -> Color.White
        else -> Color.Blue
    }
    Row (modifier = modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        ){
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            onClick = {
                viewModel.getCredentials(
                    credentialManager = credentialManager,
                    context = context,
                    onGetCredentialResponse = onGetCredentialResponse
                )
            },
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = containerColor),
            elevation = CardDefaults.elevatedCardElevation(10.dp)
        ) {
            Row (modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified,
                    modifier = modifier
                        .size(18.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "CONTINUE WITH GOOGLE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,

                )
            }

        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewContinueWithGoogleCard() {}
    /*
    ContinueWithGoogleCard(
        modifier = Modifier,
        colorScheme = 1
    )
}
     */


