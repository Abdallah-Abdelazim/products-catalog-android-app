@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.abdallah_abdelazim.product_catalog"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.testapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":shared"))

    // UI
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Glide
    implementation(libs.glide)
    ksp(libs.glide.ksp)

    // Lifecycle
    implementation(libs.viewmodel)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

}