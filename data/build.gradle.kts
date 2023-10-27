plugins {
    `android-library`
    `kotlin-android`
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.data"
}

dependencies {
    koin()
    dataStore()
    room()

    commonModule()
}