@file:Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(libs.plugins.ksp.get().pluginId) version (libs.plugins.ksp.get().version.requiredVersion) apply false
}