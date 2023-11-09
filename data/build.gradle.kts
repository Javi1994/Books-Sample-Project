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
    implementation(libs.koin.android)

    implementation(libs.datastore)

    implementation(libs.room.ktx)
    implementation(libs.room.core)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)

    implementation(project(":common"))
}