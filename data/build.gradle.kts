@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
}

apply<MainGradlePluginModule>()

android {
    namespace = "com.javi.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(libs.datastore)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    implementation(project(":common"))
}