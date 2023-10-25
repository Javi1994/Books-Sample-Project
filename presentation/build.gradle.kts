plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.presentation"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    coreKtx()
    coreUi()
    lifecycle()

    hilt()
    navigation()

    test()

    domainModule()
    commonModule()
}