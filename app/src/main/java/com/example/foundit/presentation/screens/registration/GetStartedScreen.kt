package com.example.foundit.presentation.screens.registration


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
        delay(2000)
        showCard = true
        delay(2000)
        showImage = true
        delay(2000)
        showTitle = true
        delay(5000)
        showDescription = true
        delay(3000)
        showButton = true
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            AnimatedVisibility(
                visible = showCard,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 5000)
                )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.59f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 18.dp)

                    ) {
                        AnimatedVisibility(
                            visible = showImage,
                            enter = fadeIn(animationSpec = tween(durationMillis = 2000))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.google), // Replace with your image resource
                                contentDescription = "Found it Logo",
                                modifier = Modifier.size(100.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = showTitle
                        ) {
                            TypingTextEffect(text = "Welcome to Found it", typingDelay = 220)
                        }

                        AnimatedVisibility(
                            visible = showDescription,
                            enter = fadeIn(animationSpec = tween(durationMillis = 3000))
                        ) {
                            Text(
                                text = "This app helps you track and locate your lost items quickly and efficiently. Whether you've misplaced your wallet, or any other valuable item, Found it is here to assist you in finding them with ease.",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = showButton,
                            enter = fadeIn(animationSpec = tween(durationMillis = 2000)) + scaleIn(animationSpec = tween(durationMillis = 2000))
                        ) {
                            Button(
                                onClick = {
                                    // Navigate to the next screen
                                    navController.navigate(forwardNavigation)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .padding(top = 16.dp)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGetStartedScreen() {
    GetStartedScreen(navController = NavHostController(LocalContext.current), forwardNavigation = NavRoutes.HOME)
}
