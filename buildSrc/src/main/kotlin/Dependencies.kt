import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:1.12.0"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    const val appCompat = "androidx.appcompat:appcompat:1.6.1"
    const val fragment = "androidx.fragment:fragment-ktx:1.6.1"
    const val material = "com.google.android.material:material:1.10.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"
    const val gradleToolsPlugin = "com.android.tools.build:gradle:8.1.2"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val junit = "junit:junit:4.13.2"
    const val androidJUnit = "androidx.test.ext:junit:1.1.5"
    const val espresso = "androidx.test.espresso:espresso-core:3.5.1"

    object BuildScriptPlugin {
        val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
        val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }
}

fun DependencyHandler.gradlePlugins() {
    classpath(Dependencies.BuildScriptPlugin.kotlinGradlePlugin)
    classpath(Dependencies.BuildScriptPlugin.gradlePlugin)
    classpath(Dependencies.BuildScriptPlugin.hilt)
}

fun DependencyHandler.core() {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragment)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.coroutines)
}

fun DependencyHandler.test() {
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidJUnit)
    androidTestImplementation(Dependencies.espresso)
}

fun DependencyHandler.dataModule() {
    implementation(project(":data"))
}

fun DependencyHandler.domainModule() {
    implementation(project(":domain"))
}

fun DependencyHandler.presentationModule() {
    implementation(project(":presentation"))
}