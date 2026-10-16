package com.example.grokapp.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// TODO [Aşama 3] HomeRoute stateful giriş noktasıdır. NavHost bu fonksiyonu
// çağırır, HomeScreen'i değil.
// Ne: ViewModel'i burada al. State'i topla ve HomeScreen'e parametre olarak
// geçir. HomeScreen ViewModel, repository veya NavController görmesin.
// Neden: Route/Screen ayrımı, Navigation ile çizilen UI'ı ayırır. Stateless
// ekran Preview'da ve Compose testinde graph'siz durur.
// Araştır: https://developer.android.com/topic/architecture/ui-layer
// Araştır: https://developer.android.com/guide/navigation

// TODO [Aşama 5] StateFlow'u collectAsStateWithLifecycle ile topla.
// Ne: collectAsState kullanma. Bağımlılık `lifecycle-runtime-compose`
// catalog'da yok; Aşama 2'deki catalog TODO'suna göre sen ekle.
// Neden: collectAsStateWithLifecycle varsayılan olarak lifecycle STARTED iken
// toplar, STOPPED olunca durur. collectAsState arka plandayken de akışı
// ayakta tutar.
// Araştır: https://developer.android.com/develop/ui/compose/state
// Araştır: https://developer.android.com/topic/libraries/architecture/coroutines

@Composable
fun HomeRoute() {
    Text(text = "TODO")
}
