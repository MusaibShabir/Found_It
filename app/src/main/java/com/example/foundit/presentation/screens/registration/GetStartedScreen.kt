package com.example.foundit.presentation.screens.registration


import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import com.example.foundit.R
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.ui.theme.LogoColor
import com.example.foundit.ui.theme.MainGreen
import kotlinx.coroutines.delay

@Composable
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    forwardNavigation: String,
    navController: NavHostController
) {

    var showCard by remember { mutableStateOf(false) }
    var showImage by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showDescription by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        showCard = true
        delay(100)
        showImage = true
        delay(1000)
        showTitle = true
        delay(1000)
        showDescription = true
        delay(2000)
        showButton = true
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            AnimatedVisibility(
                visible = showCard,
                enter = slideInVertically(
                    initialOffsetY = { it + 450 },
                    animationSpec = tween(durationMillis = 2000)
                )
            ) {
                OutlinedCard(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(390.dp),
                    shape = RoundedCornerShape(36.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MainGreen.copy(alpha = .15f)
                    ),
                    border = BorderStroke(color = LogoColor, width = 1.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 20.dp)

                    ) {
                        AnimatedVisibility(
                            visible = showImage,
                            enter = fadeIn(animationSpec = tween(durationMillis = 1500))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.app_name_icon),
                                contentDescription = "Found it Logo",
                            )
                        }

                        AnimatedVisibility(
                            visible = showTitle
                        ) {
                            TypingTextEffect(text = "Welcome to Found It", typingDelay = 50)
                        }

                        AnimatedVisibility(
                            visible = showDescription,
                            enter = fadeIn(animationSpec = tween(durationMillis = 3000))
                        ) {
                            Text(
                                text = "Misplaced your keys? Lost your phone? Join our community of helpful finders and seekers. " +
                                        "This app makes it easy to report lost items and connect with people who may have found them.",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = showButton,
                            enter = fadeIn(animationSpec = tween(durationMillis = 200)) + scaleIn(animationSpec = tween(durationMillis = 1000))
                        ) {
                            ElevatedButton(
                                onClick = {
                                    navController.navigate(forwardNavigation)
                                },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MainGreen,
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .padding(top = 20.dp)
                                    .height(52.dp)
                            ) {
                                Text(
                                    text = "Get Started",
                                    fontSize = 18.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    BackHandler(
        enabled = true,
    ) {

    }
}

@Composable
fun TypingTextEffect(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    typingDelay: Long = 300L
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        displayedText = ""
        text.forEachIndexed { _, char ->
            delay(typingDelay)
            displayedText += char
        }
    }

    Text(
        text = displayedText,
        style = textStyle,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}


@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
@Composable
fun PreviewGetStartedScreen() {
    GetStartedScreen(
        navController = NavHostController(LocalContext.current),
        forwardNavigation = NavRoutes.HOME
    )
}
