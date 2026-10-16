package com.example.grokapp.ui.login

// TODO [Aşama 3] Tek seferlik UI event'lerini burada modelle.
// Ne: sealed bir tip. Alt tipleri sen adlandır. LoginUiState'e navigasyon
// bayrağı koyma. `when` exhaustive olsun, `else` yazma.
// Neden: navigasyon bir durum değil, bir kez tüketilen olaydır. State olarak
// tutulursa rotation sonrası aynı geçiş tekrar oynayabilir. `else` yeni event
// eklenince derleyici uyarısını kapatır.
// Araştır: https://developer.android.com/topic/architecture/ui-layer/events
// Araştır: https://developer.android.com/topic/architecture/ui-layer/stateholders

// TODO [Aşama 3] Aşama bitince Karar notu: `docs/decisions/03-navigation-events.md`.
// Channel mı, event'i state olarak modellemek mi, onu bu nota yaz.

sealed interface LoginEvent
