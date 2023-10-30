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

    object Compose {
        const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
        const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
        const val composeGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.previewTooling}"
        const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial}"

        const val composeTestBom = "androidx.compose:compose-bom:${Versions.composeBom}"
        const val composeJUnit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val toolingDebug = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }

    object ComposeDestinations {
        const val destinationsCore =
            "io.github.raamcosta.compose-destinations:core:${Versions.composeDestinations}"
        const val destinationsKsp =
            "io.github.raamcosta.compose-destinations:ksp:${Versions.composeDestinations}"
    }

    object KoinDependencies {
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
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

fun DependencyHandler.compose() {
    implementation(Dependencies.Compose.composeActivity)
    implementation(Dependencies.Compose.composeBom)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeGraphics)
    implementation(Dependencies.Compose.composeTooling)
    implementation(Dependencies.Compose.composeMaterial)

    androidTestImplementation(Dependencies.Compose.composeTestBom)
    androidTestImplementation(Dependencies.Compose.composeJUnit4)
    debugImplementation(Dependencies.Compose.toolingDebug)
    debugImplementation(Dependencies.Compose.composeTestManifest)
}

fun DependencyHandler.composeDestinations() {
    implementation(Dependencies.ComposeDestinations.destinationsCore)
    ksp(Dependencies.ComposeDestinations.destinationsKsp)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.LifecycleDependencies.lifecycleRuntime)
    implementation(Dependencies.LifecycleDependencies.viewModelLifecycle)
}

fun DependencyHandler.koin() {
    implementation(Dependencies.KoinDependencies.koinAndroid)
    implementation(Dependencies.KoinDependencies.koinCompose)
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