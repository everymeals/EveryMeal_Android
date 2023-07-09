@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.everymeal.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp.logging.interceptor)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.testing.compiler)

    // Serialization
    implementation(libs.serialization)
    implementation(libs.kotlin.serilization)
}