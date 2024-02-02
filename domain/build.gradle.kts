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

    implementation(libs.androidx.paging.common.v320rc01)
    testImplementation(libs.junit.jupiter)
}
