package com.example.foundit.presentation.screens.registration.signup

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.registration.components.ClickableTextToNavigationRoute
import com.example.foundit.presentation.screens.registration.components.OrDivider
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogleCard
import com.example.foundit.presentation.screens.registration.components.google.ContinueWithGoogleViewModel
import com.example.foundit.ui.theme.Righteous
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel,
    continueWithGoogleViewModel: ContinueWithGoogleViewModel,
    navController: NavController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var isEmailValid by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isConfirmPasswordValid by remember { mutableStateOf(true) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("Location", "Permission granted")
            coroutineScope.launch {
                getLastLocation(fusedLocationClient, context)
            }
        } else {
            Toast.makeText(context, "Location permission is required to proceed", Toast.LENGTH_LONG).show()
        }
    }

    fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d("Location", "Permission already granted")
                coroutineScope.launch {
                    getLastLocation(fusedLocationClient, context)
                }
            }
            else -> {
                Log.d("Location", "Requesting permission")
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Create Account",
                fontSize = 36.sp,
                fontFamily = Righteous,
                fontWeight = FontWeight.Normal
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            // First Name
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                value = firstName,
                onValueChange = { firstName = it.filter { char -> char.isLetter() } },
                label = { Text("First Name") },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Person icon") },
                placeholder = { Text("Enter Your First Name", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                )
            )

            // Last Name
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                value = lastName,
                onValueChange = { lastName = it.filter { char -> char.isLetter() } },
                label = { Text("Last Name") },
                placeholder = { Text("Enter Your Last Name", fontStyle = FontStyle.Italic) },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Person icon") },
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                )
            )

            // Gender
            var expanded by remember { mutableStateOf(false) }
            val options = listOf("Male", "Female", "Other")
            ExposedDropdownMenuBox(
                modifier = modifier.fillMaxWidth(),
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = gender,
                    onValueChange = {},
                    label = { Text("Gender") },
                    leadingIcon = { Icon(Icons.Outlined.Man, contentDescription = "Gender icon") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    placeholder = { Text("Select Your Gender", fontStyle = FontStyle.Italic) },
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Blue,
                        cursorColor = Color.Blue,
                        focusedBorderColor = Color.Blue,
                        selectionColors = TextSelectionColors(
                            handleColor = Color.Blue,
                            backgroundColor = Color.Transparent,
                        ),
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                        .padding(bottom = 18.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                gender = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Email
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = if (!isEmailValid && email.isNotBlank()) 10.dp else 0.dp),
                value = email,
                onValueChange = {
                    email = it
                    isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email icon") },
                trailingIcon = {
                    if (!isEmailValid && email.isNotBlank()) {
                        Icon(
                            Icons.Filled.Error,
                            contentDescription = "Email icon",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                placeholder = { Text("Enter Your Email", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                isError = !isEmailValid,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    if (!isEmailValid && email.isNotBlank()) {
                        Text("Invalid email address", color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            // Password
            val passwordIcon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = if (!isPasswordValid && password.isNotBlank()) 10.dp else 0.dp),
                value = password,
                onValueChange = {
                    password = it
                    isPasswordValid = it.length >= 8
                },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password icon") },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(passwordIcon, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                placeholder = { Text("Enter Your Password", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = !isPasswordValid,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    if (!isPasswordValid && password.isNotBlank()) {
                        Text("Password must be at least 8 characters", color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            // Confirm Password
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = if (!isConfirmPasswordValid && confirmPassword.isNotBlank()) 10.dp else 0.dp),
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = confirmPassword != password
                    isConfirmPasswordValid = !confirmPasswordError
                },
                label = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password icon") },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(passwordIcon, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                placeholder = { Text("Confirm Your Password", fontStyle = FontStyle.Italic) },
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = confirmPasswordError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Blue,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.Blue,
                        backgroundColor = Color.Transparent,
                    ),
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                supportingText = {
                    if (confirmPasswordError) {
                        Text("Passwords do not match", color = MaterialTheme.colorScheme.error)
                    }
                }
            )


        } // End of Input Field Column

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            ElevatedButton(
                modifier = modifier
                    .width(200.dp)
                    .height(52.dp),
                onClick = {
                    signUpViewModel.signUpUser(email, password, firstName, lastName) { isSuccess ->
                        if (isSuccess) {
                            Log.d("SignUp", "User created successfully")
                            navController.navigate(NavRoutes.HOME)
                        } else {
                            Log.d("SignUp", "Authentication failed")
                        }
                    }
                },
                colors = ButtonColors(
                    containerColor = Color.Blue,
                    contentColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                enabled = firstName.isNotEmpty()
                        && lastName.isNotEmpty()
                        && gender.isNotEmpty()
                        && email.isNotEmpty()
                        && password.isNotEmpty()
                        && confirmPassword.isNotEmpty()
                        && isEmailValid
                        && isPasswordValid
                        && isConfirmPasswordValid
                        && password == confirmPassword

            ) {
                Text(
                    text = "SIGN UP",
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        } // Button Row=

        Spacer(modifier = modifier.height(10.dp))

        ContinueWithGoogleCard(
            modifier = modifier,
            //colorScheme = 1,
            continueWithGoogleViewModel = continueWithGoogleViewModel,
        ) { credential ->
            signUpViewModel.onSignUpWithGoogle(credential) { result ->
                when (result) {
                    SignUpViewModel.SignInResult.Success -> {
                        Log.d("SignUp", "User created successfully")
                        requestLocationPermission()
                        navController.navigate(NavRoutes.HOME)
                    }
                    is SignUpViewModel.SignInResult.Failure -> {
                        Log.d(
                            "SignUp",
                            "Authentication failed: Code ${result.errorCode}, Message: ${result.errorMessage}"
                        )
                    }
                }
            }
        }

        OrDivider()

        // Already have an Account
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ){

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Already have an account?")

                Spacer(modifier = modifier.width(10.dp))

                ClickableTextToNavigationRoute(
                    text = "Log in",
                    navRoute = NavRoutes.LOGIN,
                    navController = navController,
                    modifier = modifier
                )
            }


        }

        // Terms & Conditions Block
        Column (
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "By proceeding, you agree with our",
                )

            }

            Spacer(modifier = modifier.height(10.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableTextToNavigationRoute(
                    text = "Terms of Service",
                    navRoute = NavRoutes.TERMS_OF_SERVICE,
                    modifier = modifier.padding(end = 8.dp),
                    navController = navController
                )

                Text(text = "&")

                ClickableTextToNavigationRoute(
                    text = "Privacy Policy",
                    navRoute = NavRoutes.PRIVACY_POLICY,
                    modifier = modifier.padding(start = 8.dp),
                    navController = navController
                )

            }
        }



    }
}

suspend fun getLastLocation(
    fusedLocationClient: FusedLocationProviderClient,
    context: Context
) {
    if (ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        try {
            val location = fusedLocationClient.lastLocation.await() // Use await() to make it suspendable
            location?.let {
                val userLocation = LatLng(it.latitude, it.longitude)
                Log.d("Location", "User is at: $userLocation")
            } ?: run {
                Toast.makeText(context, "Location not available, make sure GPS is enabled", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            Toast.makeText(context, "Location permission is required to access location", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(context, "Location permission is not granted", Toast.LENGTH_SHORT).show()
    }
}


//@Composable
//@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_6_pro")
//fun PreviewSignUpScreen() {
//    SignUpScreen(
//        modifier = Modifier,
//        signUpViewModel = remember { SignUpViewModel() },
//        continueWithGoogleViewModel = remember { ContinueWithGoogleViewModel() },
//        navController = rememberNavController()
//    )
//}
