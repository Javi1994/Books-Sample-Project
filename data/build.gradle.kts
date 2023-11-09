plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
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
    kapt(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)

    implementation(project(":common"))
}