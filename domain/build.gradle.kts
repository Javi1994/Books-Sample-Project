@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.domain"
}

dependencies {
    implementation(libs.koin.android)

    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)

    implementation(project(":common"))
    implementation(project(":data"))
}