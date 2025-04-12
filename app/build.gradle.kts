plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // safe args Module package Javohir Oromov
    id ("androidx.navigation.safeargs.kotlin")

    // kotlin kapt
    id("kotlin-kapt")  
}

android {
    namespace = "com.example.eslatma"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.eslatma"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // ViewModel Javohir Oromov
    implementation( libs.androidx.fragment.ktx)
    implementation (libs.androidx.lifecycle.extensions)

    // ViewBinding Krich Javohir Oromov
    implementation(libs.viewbindingpropertydelegate)

    // Navigation Component Javohir Oromov
     implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Room Javohir Oromov
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    kapt (libs.androidx.room.compiler)

    // Rich - Editor Javohir Oromov
    implementation (libs.richeditor.android)

    // Circle ImageView Javohir Oromov
    implementation (libs.circleimageview)
}