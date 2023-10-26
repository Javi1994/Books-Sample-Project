plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.common"
}

dependencies {
    koin()

    coroutines()
}