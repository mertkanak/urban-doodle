package com.example.grokapp.ui.home

// TODO [Aşama 5] Home ekranının state'ini sealed yap. Alanları sen seç.
// Ne: Aşama 2'deki LoginUiState ile aynı kural. Bekleme, içerik ve hata ayrı
// alt tip. İçerik hem profili hem duyuruları taşısın; ikisi ayrı akış olarak
// ekrana sızmasın. `when` exhaustive, `else` yok.
// Neden: iki ayrı StateFlow, biri dolu biri hatalıken UI'ın ne çizeceğini
// belirsiz bırakır. Tek sealed state bu kombinasyonu derleme zamanında zorlar.
// Araştır: https://developer.android.com/topic/architecture/ui-layer/stateholders

sealed interface HomeUiState
