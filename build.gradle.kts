// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// TODO [Aşama 3] Serialization plugin'i apply false ile buraya da yazılır.
// Eklenmedi. Önce app/build.gradle.kts ve libs.versions.toml TODO'ları.
// TODO [Aşama 4] Hilt ve KSP plugin'leri apply false ile buraya yazılır.
// Eklenmedi. kapt yok.