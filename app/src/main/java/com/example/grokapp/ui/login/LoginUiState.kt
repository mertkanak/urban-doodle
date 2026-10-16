package com.example.grokapp.ui.login

// TODO [Aşama 2] LoginUiState'i sealed yap. Alt tiplerin gövdesini bu yorum
// yazmaz; alanları sen seç.
// Ne: ekranın aynı anda taşıyamayacağı durumlar ayrı alt tip olsun
// (bekleme, veri, hata gibi). Hata mesajını başarı verisinin yanına nullable
// alan olarak iliştirme.
// Neden: tek state akışı UI'ın baktığı tek yerdir. `when` ifadesi `else`
// olmadan exhaustive kalır. Yeni alt tip eklenince derleyici eksik dalı
// gösterir. `else`, o dalı sessizce yutar ve yanlış ekran çizer.
// Araştır: https://developer.android.com/topic/architecture/ui-layer/stateholders
// Araştır: https://developer.android.com/topic/architecture/ui-layer

// TODO [Aşama 2] LoginScreen bu state üzerinde `when` kursun. `else` dalı yok.

sealed interface LoginUiState
