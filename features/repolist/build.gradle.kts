@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3") version "3.8.6"
}

val localProperties = extra["localProperties"] as Properties
val githubApiToken: String = localProperties.getProperty("GITHUB_API_TOKEN")
    ?: error("Missing GITHUB_API_TOKEN. Define it in gradle.properties.local")

android {
    namespace = "com.demo.repolist"
    compileSdk = 36

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "GITHUB_API_TOKEN",
            githubApiToken
        )
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

apollo {
    service("githubRepositoryList") {
        packageName.set("com.demo.repolist")

        introspection {
            endpointUrl.set("https://api.github.com/graphql")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
            headers.put("Authorization", "Bearer $githubApiToken")
        }
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

    // Apollo
    implementation("com.apollographql.apollo3:apollo-runtime:3.8.6")
}
