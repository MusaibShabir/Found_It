package com.example.foundit.presentation.screens.registration.components.google

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import com.example.foundit.R
import com.example.foundit.ui.theme.RobotFamily

@Composable
fun ContinueWithGoogleCard(
    modifier: Modifier,
    //colorScheme: Int = 1,
    continueWithGoogleViewModel: ContinueWithGoogleViewModel,
    onGetCredentialResponse: (Credential) -> Unit
) {
    val context = LocalContext.current
    val credentialManager = CredentialManager.create(context)

    /*
    For Dynamic Card & Text Color

    val containerColor = when(colorScheme) {
        1 -> Color.White
        2 -> Color.White
        else -> Color.White
    }
    val textColor = when (colorScheme) {
        1 -> Color.Blue
        2 -> Color.White
        else -> Color.Blue
    }

     */
    Row (modifier = modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        ){
        OutlinedCard(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            onClick = {
                continueWithGoogleViewModel.getCredentials(
                    credentialManager = credentialManager,
                    context = context,
                    onGetCredentialResponse = onGetCredentialResponse
                )
            },
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(width = 1.dp, color = Color.Black),
            colors = CardDefaults.elevatedCardColors(),
            elevation = CardDefaults.elevatedCardElevation(25.dp)
        ) {
                Row (modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google Icon",
                        tint = Color.Unspecified,
                        modifier = modifier
                            .size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "CONTINUE WITH GOOGLE",
                        fontSize = 18.sp,
                        letterSpacing = 2.sp,
                        fontFamily = RobotFamily,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center

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


