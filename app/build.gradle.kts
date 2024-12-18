plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.foundit"
    compileSdk = 34

    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "Musaibkey"
            storeFile = file("C:\\Users\\user\\Desktop\\apps\\foundit_jks.jks")
            storePassword = "Musaibkey"

        }
    }
    defaultConfig {
        applicationId = "com.example.foundit"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            getByName("release") {
                signingConfig = signingConfigs.getByName("release")
            }
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "35.0.0"


}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    //Play Feature Delivery
    implementation(libs.feature.delivery)

    //Room
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.appcompat)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)


    //Coil
    implementation(libs.coil.compose)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.compose)

    // Feature module Support
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    // Jetpack Compose Integration
    implementation(libs.androidx.navigation.compose)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.config.ktx)
    implementation(libs.firebase.firestore.ktx)


    //Authentication with Credential Manager
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.play.services.auth)  // Android 13 or below
    implementation(libs.googleid)
    implementation(libs.play.services.auth)

    //Google Maps
    implementation(libs.play.services.maps)
    implementation(libs.maps.compose)
    implementation(libs.maps.compose.utils)
    implementation(libs.maps.compose.widgets)
    implementation(libs.maps.ktx)
    implementation(libs.maps.utils.ktx)
    implementation(libs.play.services.location)

    //Permissions Accompanist
    implementation(libs.accompanist.permissions)

    // For making HTTP requests (OkHttp)
    implementation (libs.okhttp)

    // For parsing JSON (Gson or kotlinx.serialization)
    implementation (libs.kotlinx.serialization.json)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



}