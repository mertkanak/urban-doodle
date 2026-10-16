package com.example.grokapp.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// TODO [Aşama 3] Bu fonksiyon stateless kalır.
// Ne: parametre olarak HomeUiState ve olay lambda'ları gelsin. ViewModel
// oluşturma, repository çağrısı ve navigate burada yok.
// Neden: Route state'i toplar, Screen çizer. Screen, ViewModel'siz test edilir.
// Araştır: https://developer.android.com/topic/architecture/ui-layer

// TODO [Aşama 5] Duyuruları LazyColumn ile çiz. Her öğeye stabil key ver.
// Ne: key, listenin index'i olmasın. Announcement üzerinde değişmeyen bir id
// kullan. Liste başına ekleme ve silmeyi dene.
// Neden: key yokken Compose öğe state'ini index ile eşler. Baştan silinen
// satır, alttaki satırın state'ini taşır. index'i key yapmak da aynı hatadır.
// Araştır: https://developer.android.com/develop/ui/compose/lists

// TODO [Aşama 5] HomeUiState üzerinde `when` exhaustive olsun. `else` dalı yok.
// Neden: yeni alt tip eklenince derleyici eksik dalı gösterir. `else` bu
// hatayı sessizce yutar.
// Araştır: https://developer.android.com/topic/architecture/ui-layer/stateholders

@Composable
fun HomeScreen() {
    Text(text = "TODO")
}
