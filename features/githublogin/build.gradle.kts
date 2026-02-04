@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    // Koin
    implementation("io.insert-koin:koin-core:4.1.1")
    implementation("io.insert-koin:koin-android:4.1.1")

    // AppAuth
    implementation("net.openid:appauth:0.11.1")

    // Encryption
    implementation("androidx.security:security-crypto:1.1.0")
}
