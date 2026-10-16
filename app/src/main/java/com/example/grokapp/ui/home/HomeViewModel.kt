package com.example.grokapp.ui.home

// TODO [Aşama 5] HomeViewModel yüklemeyi koordine eder. Composable içinden
// repository çağırma.
// Ne: profil ve duyuruları paralel başlat. Profil 1500 ms, duyurular 1000 ms
// sürer. Paralel bitiş yavaş olan kadardır, yaklaşık 1500 ms. Sıralı bekleme
// yaklaşık 2500 ms sürer ve bu aşamada yanlış kabul edilir. İki sonucu tek
// HomeUiState'te birleştir. Kullanıcı adı tam olarak "error" ise hata state'i.
// CancellationException'ı yakalayıp yutma. Bu tip Exception altındadır; genel
// catch iptali bozar ve viewModelScope temizlenmez. İptal, hata state'i
// değildir. Yakalanırsa yeniden fırlat; daha iyisi yalnız beklediğin tipi
// yakala (örneğin IOException).
// Neden: birleşik state UI'ın tek bakacağı yerdir. Paralel işin gerçekten
// paralel olduğu ancak süre ölçülünce belli olur.
// Araştır: https://developer.android.com/kotlin/coroutines/coroutines-best-practices
// Araştır: https://developer.android.com/topic/architecture/ui-layer

// TODO [Aşama 5] SavedStateHandle ile process death'i karşıla.
// Ne: rotation'da ViewModel zaten hayatta kalır. Process death'te sınıftan
// geri gelen, SavedStateHandle'a yazdığın küçük değerlerdir (kullanıcı adı
// veya seçili öğe id'si). Handle'ı constructor'dan al. Activity'den elle
// iletme.
// Neden: `remember` process death'te kaybolur. ViewModel da process death'te
// yeniden kurulur. SavedStateHandle saved state registry'ye bağlıdır.
// Araştır: https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate

// TODO [Aşama 5] Bu aşamada en az bir test yaz.
// Ne: `runTest` ile sanal zaman. Paralel yükleme yaklaşık 1500 ms sürsün,
// 2500 ms değil. Gerçek `delay` ile 1.5 saniye uyuma. "error" kullanıcı adı
// hata üretsin. Ayrı senaryo: iptal edilen iş CancellationException'ı hata
// state'ine çevirmesin.
// Araştır: https://developer.android.com/kotlin/coroutines/test

// TODO [Aşama 5] CPU Profiler, System Trace ve App Inspection ile bu yüklemeye bak.
// Ne: 1500 ms'lik beklemeyi CPU meşguliyeti sanma. System Trace beklemenin
// uyku mu çalışma mı olduğunu gösterir. App Inspection penceresi Network
// Inspector ve Database Inspector sekmelerini taşır; bu aşamada ağ yoktur,
// sekmelerin boş kalması beklenen sonuçtur. Aşama 6'da aynı pencereye dön.
// Araştır: https://developer.android.com/studio/profile/cpu-profiler
// Araştır: https://developer.android.com/topic/performance/tracing
// Araştır: https://developer.android.com/android-performance-analyzer
// Araştır: https://developer.android.com/studio/debug/network-profiler

// TODO [Aşama 5] Aşama bitince Karar notu: `docs/decisions/05-home-async-savedstate.md`.

class HomeViewModel
