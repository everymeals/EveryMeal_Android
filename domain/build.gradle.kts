plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Hilt
    implementation(libs.hilt.core)

    // Coroutines
    implementation(libs.kotlin.coroutines)

    implementation("androidx.paging:paging-common:3.2.0-rc01")
}
