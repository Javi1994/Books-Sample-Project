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

    koin()
    navigation()

    test()

    domainModule()
    commonModule()
}