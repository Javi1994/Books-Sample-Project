plugins {
    `android-library`
    `kotlin-android`
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