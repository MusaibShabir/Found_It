package com.example.foundit.ui.theme

import androidx.compose.material3.Typography
<<<<<<< HEAD
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
=======
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foundit.R
>>>>>>> b05e32c (Initial commit)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
<<<<<<< HEAD
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
=======
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = FontWeight.Normal,
    ),
>>>>>>> b05e32c (Initial commit)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
<<<<<<< HEAD
=======
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_thin_italic)),
        fontWeight = FontWeight.Thin
    )
)

val RobotFamily = FontFamily(

    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),

    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_black_italic, FontWeight.Black, FontStyle.Italic),

    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_bold_italic, FontWeight.Bold,FontStyle.Italic),



    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_light_italic, FontWeight.Light,FontStyle.Italic),

    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_medium_italic, FontWeight.Medium,FontStyle.Italic),

    Font(R.font.roboto_thin_italic, FontWeight.Thin,FontStyle.Italic),
    Font(R.font.roboto_thin_italic, FontWeight.Thin),

>>>>>>> b05e32c (Initial commit)
)