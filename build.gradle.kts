buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.

@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.navigation.safeargs) apply false
    alias(libs.plugins.ksp) apply false
}

run {} // This is a workaround for https://youtrack.jetbrains.com/issue/KTIJ-19369
