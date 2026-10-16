pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GrokApp"
include(":app")

// TODO [Aşama 8] include yalnız :app kalsın. core, feature-login, feature-home
// ve convention plugin bu aşamada gelir. Şimdi modül ekleme.
// Ne: feature modülleri birbirinin impl'ine değil, core'daki sözleşmeye bağlanır.
// Mevcut local unit test feature-login'e taşınınca da geçer. Buna ek olarak
// core içinde en az bir test yaz. Bu, aşamanın zorunlu testidir.
// Neden: sınırlar kod oturmadan modül, döngüsel bağımlılık üretir. Tek modül
// bu yüzden kasıtlı başlangıçtır.
// Araştır: https://developer.android.com/topic/modularization
// Araştır: https://developer.android.com/topic/modularization/patterns
//
// R8, build type, product flavor, signing, APK ve AAB bu aşamanın araçlarıdır.
// assembleRelease APK üretir. bundleRelease AAB üretir. Play App Signing'de
// upload key senin imzaladığın anahtardır; dağıtım anahtarını Play tutar.
// Araştır: https://developer.android.com/topic/performance/app-optimization/enable-app-optimization
// Araştır: https://developer.android.com/build/build-variants
// Araştır: https://developer.android.com/studio/publish/app-signing
// Araştır: https://developer.android.com/guide/app-bundle
// Araştır: https://developer.android.com/studio/build/building-cmdline
// Araştır: https://developer.android.com/studio/publish
//
// TODO [Aşama 8] Aşama bitince Karar notu: `docs/decisions/08-multi-module.md`.

// TODO [Aşama 9] Arama, sayfalama ve form doğrulama, multi-module bittikten
// sonraki ikinci feature'dır. Modülünü şimdi açma.
// Ne: arama sorgusu, sayfalı liste ve doğrulanmış form. Üçünden her biri için
// ayrı state düşün. En az bir test yaz: form kuralı için local unit test veya
// sayfa yüklemesi için bir test. İkisi de yoksa aşama bitmez.
// Neden: ilk feature dikey bir dilimdir. İkincisi, aynı modül sınırının yeni
// bir işi taşıyıp taşımadığını gösterir.
// Araştır: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
// Araştır: https://developer.android.com/develop/ui/compose/text/user-input
// Araştır: https://developer.android.com/develop/ui/compose/lists
//
// TODO [Aşama 9] Aşama bitince Karar notu: `docs/decisions/09-second-feature.md`.
