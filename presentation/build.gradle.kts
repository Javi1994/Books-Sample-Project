plugins {
    `android-library`
    `kotlin-android`
    id("com.google.devtools.ksp") version Versions.ksp
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.presentation"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(libs.core.ktx)

    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.tooling.preview)
    implementation(libs.material3)

    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    implementation(project(":common"))
    implementation(project(":domain"))
}