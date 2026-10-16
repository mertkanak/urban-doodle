package com.example.grokapp.di

// TODO [Aşama 4] Hilt @Binds ile HomeRepository'yi FakeHomeRepository'ye bağla.
// Ne: abstract @Binds fonksiyonu somut tipi alır, arayüz olarak verir.
// @Provides ile elle kurma. ViewModel'de constructor injection. Hangi
// @InstallIn scope'unun doğru olduğunu Hilt sayfasından seç.
// Bu dosyada annotation yok. Hilt ve KSP bağımlılığı eklenmedi; eklemeden
// annotation yazmak derlemeyi kırar. Eski kapt kurulumunu kopyalama. AGP 9
// built-in Kotlin kullanıyor; plugin satırını AGP 9 notuyla karşılaştır.
// Neden: @Binds, tek impl için @Provides'tan daha az üretilmiş kod ister ve
// somut sınıfın constructor'ını Hilt'e bırakır. Activity repository'yi new'lemez.
// Araştır: https://developer.android.com/training/dependency-injection/hilt-android
// Araştır: https://developer.android.com/build/migrate-to-ksp
// Araştır: https://developer.android.com/build/releases/agp-9-0-0-release-notes

// TODO [Aşama 4] KSP, Build Analyzer ve Lint.
// Ne: Hilt işlemcisini KSP ile bağla. Build Analyzer'da bu plugin'in süreye
// etkisine bak. Android Lint'i çalıştır. Detekt ve ktlint developer.android.com
// aracı değildir; Lint sayfasını esas al, diğerini Karar notunda alternatif
// olarak yaz.
// Araştır: https://developer.android.com/studio/build/build-analyzer
// Araştır: https://developer.android.com/studio/write/lint

// TODO [Aşama 4] Hassas küçük veriyi DataStore ve Android Keystore ile düşün.
// Ne: Jetpack Security EncryptedSharedPreferences deprecated. Cryptography
// sayfası, security-crypto API'lerinin 1.1.0 kararlı sürümünde deprecated
// olduğunu ve bu kütüphanenin sonraki sürümü olmayacağını yazar. Bunu
// aşağıdaki sayfalardan kendin doğrula; bu yorumu tek kaynak sayma.
// DataStore'un güncel şifreleme seçeneğini de o sayfadan kontrol et. Hassas
// değer için Keystore ile DataStore'u karşılaştır. Bu scaffold'a o kodu
// yazma; karar, Karar notuna girer.
// Neden: yedekten dönen şifreli SharedPreferences, anahtar gidince açılamaz.
// Araştır: https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences
// Araştır: https://developer.android.com/privacy-and-security/cryptography
// Araştır: https://developer.android.com/topic/libraries/architecture/datastore
// Araştır: https://developer.android.com/privacy-and-security/keystore

// TODO [Aşama 6] Gerçek impl gelince tek @Binds yetmeyebilir. Fake ile
// Room/Retrofit impl arasında qualifier mı, build type mı kullanacağını
// `docs/decisions/06-data-layer.md` içine yaz. Kodu bu yorumun yerine koyma.
// Araştır: https://developer.android.com/training/dependency-injection/hilt-android

class RepositoryModule
