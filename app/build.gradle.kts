plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

// TODO [Aşama 1] Plugin listesi şimdilik yalnız android.application ve
// kotlin.compose. Yeni plugin'i önce gradle/libs.versions.toml [plugins]
// bölümüne yaz, sonra burada alias ile uygula. Kök build.gradle.kts içinde
// apply false gerekir. Sürümü catalog dışında tekrarlama.
// Araştır: https://developer.android.com/build/migrate-to-catalogs
//
// TODO [Aşama 3] Kotlin serialization plugin'i eklenmedi. Type-safe route
// için önce şu sayfayı oku, sonra catalog'a kendin ekle:
// https://developer.android.com/guide/navigation/design/type-safety
//
// TODO [Aşama 4] Hilt ve KSP plugin'leri eklenmedi. kapt ekleme. AGP 9
// built-in Kotlin kullanıyor; eski Kotlin Android plugin satırını kopyalama.
// Araştır: https://developer.android.com/training/dependency-injection/hilt-android
// Araştır: https://developer.android.com/build/migrate-to-ksp
// Araştır: https://developer.android.com/build/releases/agp-9-0-0-release-notes

android {
    namespace = "com.example.grokapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.grokapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // TODO [Aşama 2] lifecycle-viewmodel-compose ve lifecycle-runtime-compose
    // burada yok. Catalog TODO'sundan sonra libs referansı ile ekle.
    // TODO [Aşama 3] navigation-compose ve kotlinx-serialization-json yok.
    // TODO [Aşama 4] Hilt artifact'leri yok.
    // TODO [Aşama 5] kotlinx-coroutines-test, testImplementation olarak yok.
    // TODO [Aşama 6] Retrofit, OkHttp ve Room yok.
    // TODO [Aşama 7] Macrobenchmark ve Baseline Profile bağımlılığı yok.
    //   Resmi sayfa bunları ayrı bir test modülünde kurar. O modül, Aşama 8'deki
    //   feature bölünmesi değildir; feature modülü açma. Kurulumu sayfadan yap.
    //   https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}