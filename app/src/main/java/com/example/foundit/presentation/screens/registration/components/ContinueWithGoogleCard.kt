package com.example.foundit.presentation.screens.registration.components

import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.foundit.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch

@Composable
fun ContinueWithGoogleCard(
    modifier: Modifier,
    colorScheme: Int = 1,
    onGetCredentialResponse: (Credential) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
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
                val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                coroutineScope.launch {
                    try {
                        val result = credentialManager.getCredential(
                            request = request,
                            context = context
                        )

                        onGetCredentialResponse(result.credential)
                    } catch (e: GetCredentialException) {
                        Log.d("error", e.message.orEmpty())
                    }
                }
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
                Spacer(modifier = Modifier.width(10.dp))
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


