package com.example.grokapp.model

// TODO [Aşama 4] Duyuruyu bu pakette modelle. Paket adı `dto` olmayacak.
// Ne: listenin gösterdiği alanlar. Aşama 5'te LazyColumn key'i için değişmeyen
// bir id ekle. id'yi listenin index'inden üretme.
// Neden: index, ekleme ve silmede kayar. Stabil id, öğe state'inin doğru
// satırda kalmasını sağlar. Ağ/Room şeması Aşama 6'da ayrı tip olur.
// Araştır: https://developer.android.com/topic/architecture/data-layer
// Araştır: https://developer.android.com/develop/ui/compose/lists

class Announcement
