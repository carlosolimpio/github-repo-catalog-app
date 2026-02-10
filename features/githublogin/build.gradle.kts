@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.demo.githublogin"
    compileSdk = 36

    val localProperties = extra["localProperties"] as Properties

    val githubClientId = localProperties.getProperty("GITHUB_CLIENT_ID")
        ?: error("Missing GITHUB_CLIENT_ID. Define it in gradle.properties.local")

    val githubClientSecret = localProperties.getProperty("GITHUB_CLIENT_SECRET")
        ?: error("Missing GITHUB_CLIENT_SECRET. Define it in gradle.properties.local")

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

         buildConfigField(
             "String",
             "GITHUB_CLIENT_ID",
             githubClientId
         )
         buildConfigField(
             "String",
             "GITHUB_CLIENT_SECRET",
             githubClientSecret
         )
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
}

kotlin {
    jvmToolchain(18)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // AppAuth
    implementation(libs.appauth)

    // Encryption
    implementation(libs.androidx.security.crypto)
}
