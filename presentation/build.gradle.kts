plugins {
    `android-library`
    `kotlin-android`
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
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
    coreKtx()
    lifecycle()

    koin()

    compose()
    composeDestinations()

    domainModule()
    commonModule()
}