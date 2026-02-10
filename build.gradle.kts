import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
}

subprojects {

    val localProperties: Properties by lazy {
        Properties().apply {
            val file = rootProject.file("local.properties")
            if (file.exists()) {
                file.inputStream().use { load(it) }
            }
        }
    }

    extensions.extraProperties["localProperties"] = localProperties
}
