import com.android.tools.profgen.getClassDescriptorFromBinaryName
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
        const val composeMaterial =
            "androidx.compose.material3:material3:${Versions.composeMaterial}"

        const val composeTooling =
            "androidx.compose.ui:ui-tooling-preview:${Versions.previewTooling}"
        const val toolingDebug = "androidx.compose.ui:ui-tooling:${Versions.previewTooling}"
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
        const val androidArchTestCore = "androidx.arch.core:core-testing:${Versions.lifecycle}"
        const val androidTestCore = "androidx.test:core:${Versions.android}"

        const val jUnit = "junit:junit:${Versions.junit}"

        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val truth = "com.google.truth:truth:${Versions.truth}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
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
    implementation(Dependencies.Compose.composeMaterial)

    debugImplementation(Dependencies.Compose.toolingDebug)
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

fun DependencyHandler.unitTest() {
    implementation(Dependencies.TestDependencies.androidTestCore)
    testImplementation(Dependencies.TestDependencies.jUnit)
    testImplementation(Dependencies.TestDependencies.androidArchTestCore)
    testImplementation(Dependencies.TestDependencies.coroutinesTest)
    testImplementation(Dependencies.TestDependencies.truth)
    testImplementation(Dependencies.TestDependencies.mockk)
    debugImplementation(Dependencies.TestDependencies.composeTestManifest)
}

fun DependencyHandler.androidTest() {
    androidTestImplementation(Dependencies.TestDependencies.androidTestCore)
    androidTestImplementation(Dependencies.TestDependencies.jUnit)
    androidTestImplementation(Dependencies.TestDependencies.androidArchTestCore)
    androidTestImplementation(Dependencies.TestDependencies.coroutinesTest)
    androidTestImplementation(Dependencies.TestDependencies.truth)
    androidTestImplementation(Dependencies.TestDependencies.mockk)
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