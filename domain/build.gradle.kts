plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.domain"
}

dependencies {
    hilt()

    dataModule()
    commonModule()
}