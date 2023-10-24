plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.data"
}

dependencies {
    hilt()
    dataStore()
    room()

    commonModule()
}