package com.example.grokapp.data

// TODO [Aşama 4] HomeRepository'nin sahte impl'i. Ağ ve veritabanı yok.
// Neden: UI ve ViewModel gerçek kaynağı beklemeden gelişir. Aşama 4 testi
// de aynı sözleşmeyi, ağ açmadan çağırır.
// Araştır: https://developer.android.com/topic/architecture/data-layer

// TODO [Aşama 4] Bu aşamada en az bir test yaz.
// Ne: fake, sözleşmedeki başarılı yolu döner. Ağ yok, delay şartı yok; o
// Aşama 5'te gelir.
// Araştır: https://developer.android.com/training/testing/local-tests

// TODO [Aşama 5] Gecikmeleri burada kur.
// Ne: profil 1500 ms, duyurular 1000 ms. HomeViewModel ikisini aynı anda
// başlatırsa süre yaklaşık 1500 ms; sırayla beklerse yaklaşık 2500 ms.
// Kullanıcı adı tam olarak "error" ise yükleme hata fırlatır. Başka bir
// string hata sayılmaz.
// Neden: sayılar süs değildir. Aşama 5 testi sanal zamanda bu farkı kanıtlar.
// CancellationException fırlatılırsa onu burada yakalayıp yutma.
// Araştır: https://developer.android.com/kotlin/coroutines/test
// Araştır: https://developer.android.com/kotlin/coroutines/coroutines-best-practices

// TODO [Aşama 4] Aşama bitince Karar notu: `docs/decisions/04-repository-hilt.md`.

class FakeHomeRepository : HomeRepository
