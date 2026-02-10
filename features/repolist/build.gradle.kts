@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.apollo.graphql)
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // Apollo
    implementation(libs.apollo.runtime)
}
