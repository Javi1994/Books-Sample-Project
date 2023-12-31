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
    implementation(libs.bundles.koin)

    testImplementation(libs.bundles.test)

    implementation(project(":common"))
    implementation(project(":data"))
}