import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

private object Dependencies {

    object BuildScriptPlugin {
        const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object CoreDependencies {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    }

    object LifecycleDependencies {
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object CoreAndroidUiDependencies {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.android}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.android}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object HiltDependencies {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object NavigationDependencies {
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object CoroutinesDependencies {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object TestDependencies {
        const val junit = "junit:junit:${Versions.junit}"
        const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }
}

fun DependencyHandler.gradlePlugins() {
    classpath(Dependencies.BuildScriptPlugin.kotlinGradlePlugin)
    classpath(Dependencies.BuildScriptPlugin.gradlePlugin)
    classpath(Dependencies.BuildScriptPlugin.hiltGradlePlugin)
}

fun DependencyHandler.coreKtx() {
    implementation(Dependencies.CoreDependencies.coreKtx)
}

fun DependencyHandler.coreUi() {
    implementation(Dependencies.CoreAndroidUiDependencies.appCompat)
    implementation(Dependencies.CoreAndroidUiDependencies.fragment)
    implementation(Dependencies.CoreAndroidUiDependencies.material)
    implementation(Dependencies.CoreAndroidUiDependencies.constraintLayout)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.LifecycleDependencies.lifecycleRuntime)
}


fun DependencyHandler.hilt() {
    implementation(Dependencies.HiltDependencies.hiltAndroid)
    kapt(Dependencies.HiltDependencies.hiltCompiler)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.NavigationDependencies.navigationFragment)
    implementation(Dependencies.NavigationDependencies.navigationUi)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.CoroutinesDependencies.coroutines)
}

fun DependencyHandler.test() {
    testImplementation(Dependencies.TestDependencies.junit)
    androidTestImplementation(Dependencies.TestDependencies.androidJUnit)
    androidTestImplementation(Dependencies.TestDependencies.espresso)
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