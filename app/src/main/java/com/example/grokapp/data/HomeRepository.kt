package com.example.grokapp.data

// TODO [Aşama 4] Bunu Home verisinin sözleşmesi yap. Gövde yok; yalnız
// fonksiyon imzaları. Fake, Retrofit veya Room tipi bu dosyada görünmez.
// Neden: ViewModel implementasyonu değil sözleşmeyi bilir. Test fake bağlar,
// üretim başka impl bağlar. Tek modülde bile bu sınır DI'ı anlamlı kılar.
// Araştır: https://developer.android.com/topic/architecture/data-layer

// TODO [Aşama 5] Sözleşme, profil ve duyuru yüklemesini ayırsın ki ViewModel
// ikisini paralel başlatabilsin. Süreler ve "error" kullanıcı adı Fake
// içindedir; arayüze delay yazma.
// Araştır: https://developer.android.com/kotlin/coroutines/coroutines-best-practices

// TODO [Aşama 6] Aynı arayüzün arkasına Retrofit ve Room gelecek.
// Ne: arayüz HTTP Response, Room Entity veya DTO döndürmesin. Hata modeli
// ya kotlin.Result ya da sealed error olsun. Hangisi olduğunu Karar notuna
// yaz; ikisini birden gövde olarak kurma. Ağ kesilince okuma, yerel depodan
// devam etsin (offline-first). Yazma çakışmasını da o sayfadan seç.
// Neden: UI katmanı HTTP kodu ve tablo şeması görmez. offline-first'de
// okumanın kaynağı yerel depodur; ağ onu günceller.
// Araştır: https://developer.android.com/topic/architecture/data-layer/offline-first
// Araştır: https://developer.android.com/training/data-storage/room
// Araştır: https://developer.android.com/topic/architecture/data-layer

// TODO [Aşama 6] Bu aşamada en az bir test yaz.
// Ne: hata eşlemesi için local unit test (HTTP hatası veya ağ yok, senin
// sealed error ya da Result tipine dönsün). Room kullanırsan DAO için
// in-memory instrumented test de yazılabilir. İkisinden en az biri zorunlu.
// Araştır: https://developer.android.com/training/testing/local-tests
// Araştır: https://developer.android.com/training/data-storage/room

// TODO [Aşama 6] Network Inspector ve Database Inspector ile sahte gecikmeyi
// gerçek ağ ve SQL süresinden ayır. İkisi de App Inspection penceresindedir.
// Araştır: https://developer.android.com/studio/debug/network-profiler
// Araştır: https://developer.android.com/studio/inspect/database

// TODO [Aşama 6] Aşama bitince Karar notu: `docs/decisions/06-data-layer.md`.

interface HomeRepository
