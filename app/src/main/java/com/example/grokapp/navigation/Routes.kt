package com.example.grokapp.navigation

// TODO [Aşama 3] Type-safe route tiplerini sen yaz. @Serializable bu dosyada
// yok: Kotlin serialization plugin'i ve kotlinx-serialization bağımlılığı
// kasıtlı olarak eklenmedi. Annotation'ı, catalog TODO'sunu uygulamadan
// yazarsan proje derlenmez.
// Ne: argümansız hedef için object, argümanlı hedef için data class düşün.
// String route biriktirme. Hangi tiplerin route olacağına Karar notunda karar ver.
// Neden: string route içindeki yazım hatasını derleyici tutmaz. Tip, nav
// argument'i imzada taşır.
// Araştır: https://developer.android.com/guide/navigation/design/type-safety
//
// Navigation 3 ayrı bir API'dir (NavKey, NavDisplay). Bu aşamanın hedefi
// yukarıdaki type-safe route sayfasıdır. İkisini de oku, seçimini Karar
// notuna yaz. navigation-3 skill'inden kod yapıştırma.
// Araştır: https://developer.android.com/guide/navigation/navigation-3

class Routes
