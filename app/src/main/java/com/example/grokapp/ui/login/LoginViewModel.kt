package com.example.grokapp.ui.login

// TODO [Aşama 2] LoginViewModel, LoginUiState'i StateFlow olarak tutar.
// Ne: Aşama 1'deki `remember` state'ini buraya taşı. UI aksiyonlarını fonksiyon
// olarak al, state'i bu sınıfta güncelle. Composable içinde iş kuralı bırakma.
// Sınıfın ViewModel olması için gereken lifecycle bağımlılığı catalog'da yok;
// onu sen ekle. Bu dosyaya hazır kurucu veya hazır state yazılmadı.
// Neden: ViewModel configuration change'i yaşatır ve Android UI olmadan test
// edilir. StateFlow yeni collector'a son değeri verir; ekran yeniden
// bağlanınca state kaybolmaz. SharedFlow son değeri tutmaz, bu yüzden ekran
// state'i için uygun değildir.
// Araştır: https://developer.android.com/topic/architecture/ui-layer/stateholders
// Araştır: https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
// Araştır: https://developer.android.com/topic/libraries/architecture/viewmodel

// TODO [Aşama 2] Bu aşamada en az bir test yaz.
// Ne: `app/src/test` altında local unit test. Boş alanla giriş denemesi hata
// state'ine, senin seçtiğin geçerli giriş ise başarıya gitsin. Hangi alan
// kurallarını seçeceğini Karar notuna yaz. `ExampleUnitTest` bu test değildir.
// Neden: StateFlow geçişi, composable ayağa kalkmadan kilitlenir.
// Araştır: https://developer.android.com/training/testing/local-tests

// TODO [Aşama 2] Layout Inspector'da recomposition sayısına bak.
// Ne: state değişince yalnız ilgili composable recompose olsun. Parametreleri
// stable tut. Unstable parametre, içerik değişmeden de recomposition üretir.
// Araştır: https://developer.android.com/studio/debug/layout-inspector
// Araştır: https://developer.android.com/develop/ui/compose/performance/stability

// TODO [Aşama 3] One-shot navigation için Channel kullan.
// Ne: kapasitesi BUFFERED olan bir Channel. Dışarıya receiveAsFlow() ile aç.
// SharedFlow(replay = 0) kullanma.
// Neden: SharedFlow replay = 0 geçmişi tutmaz. Emit anında collector yoksa
// event düşer; login bitip navigation event'i, collector kısa süre kopukken
// kaybolur. replay = 1 yaparsan yeni collector (rotation sonrası) aynı
// navigasyonu tekrar oynatır. BUFFERED Channel, collector yokken event'i
// buffer'da tutar. receiveAsFlow her event'i bir kez teslim eder, replay yok.
// Resmi events sayfası bazı olayları UI state olarak modellemeyi de anlatır.
// Bu aşamada navigasyonu Channel ile kur. State alternatifini okuyup Karar
// notuna yaz.
// Araştır: https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
// Araştır: https://developer.android.com/topic/architecture/ui-layer/events

// TODO [Aşama 2] Aşama bitince Karar notu: `docs/decisions/02-viewmodel-uistate.md`.

class LoginViewModel
