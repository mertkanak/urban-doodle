package com.example.grokapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// TODO [Aşama 3] NavHost'u burada kur. MainActivity içindeki Greeting duruyor;
// onu sen değiştir. Bu fonksiyon, navigation bağımlılığı eklenene kadar
// derlensin diye bir Text çiziyor. navigation-compose catalog'da yok.
// Ne: başlangıç hedefi login. Başarılı girişte home'a geç ve login'i back
// stack'ten çıkar: popUpTo içinde inclusive = true. inclusive = false yalnız
// hedefin altını çıkarır, login stack'te kalır ve geri tuşu forma döner.
// Neden: girişten sonra geri, formu tekrar göstermemeli.
// Araştır: https://developer.android.com/guide/navigation/backstack
// Araştır: https://developer.android.com/guide/navigation

// TODO [Aşama 3] Bu aşamada en az bir test yaz.
// Ne: login başarı event'i tek bir navigasyon üretsin. İkinci collector aynı
// event'i yeniden görmesin. Bunu LoginViewModel akışı üzerinde local unit
// test olarak kur. NavHost'un kendisini test edeceksen navigation-testing
// sayfasını oku; bağımlılığı sen ekle.
// Araştır: https://developer.android.com/guide/navigation/testing
// Araştır: https://developer.android.com/training/testing/local-tests

// TODO [Aşama 3] LeakCanary ve Memory Profiler.
// Ne: login -> home geçişinden sonra login ekranının sızmadığını kontrol et.
// LeakCanary developer.android.com aracı değildir; kurulumunu kendi
// dokümanından doğrula. Resmi bellek aracı Memory Profiler'dır.
// Araştır: https://developer.android.com/studio/profile/memory-profiler

@Composable
fun AppNavHost() {
    Text(text = "TODO")
}
