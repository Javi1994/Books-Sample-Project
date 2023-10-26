import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

private object Dependencies {

    object BuildScriptPlugin {
        const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
    }

    object CoreDependencies {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    }

    object LifecycleDependencies {
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewModelLifecycle =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object CoreAndroidUiDependencies {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.android}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.android}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object KoinDependencies {
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
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

    object DataStoreDependencies {
        const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    }

    object RoomDependencies {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
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
}

fun DependencyHandler.gradlePluginsImplementation() {
    implementation(Dependencies.BuildScriptPlugin.kotlinGradlePlugin)
    implementation(Dependencies.BuildScriptPlugin.gradlePlugin)
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
    implementation(Dependencies.LifecycleDependencies.viewModelLifecycle)
}


fun DependencyHandler.koin() {
    implementation(Dependencies.KoinDependencies.koinAndroid)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.NavigationDependencies.navigationFragment)
    implementation(Dependencies.NavigationDependencies.navigationUi)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.CoroutinesDependencies.coroutines)
}

fun DependencyHandler.dataStore() {
    implementation(Dependencies.DataStoreDependencies.dataStore)
}

fun DependencyHandler.room() {
    implementation(Dependencies.RoomDependencies.room)
    kapt(Dependencies.RoomDependencies.roomCompiler)
    implementation(Dependencies.RoomDependencies.roomKtx)
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

fun DependencyHandler.commonModule() {
    implementation(project(":common"))
}