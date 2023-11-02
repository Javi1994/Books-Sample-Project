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
    coreKtx()
    lifecycle()

    koin()

    compose()
    composeDestinations()

    unitTest()
    androidTest()

    domainModule()
    commonModule()
}