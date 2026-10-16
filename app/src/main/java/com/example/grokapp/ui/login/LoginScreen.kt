package com.example.grokapp.ui.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// TODO [Aşama 1] Login ekranını burada, ViewModel olmadan kur.
// Ne: kullanıcı adı, şifre ve giriş düğmesi. Bu üç değerin state'ini
// `remember` ile composable içinde tut. `LoginViewModel` bu aşamada çağrılmaz.
// Neden: state'in ömrünü görmek için önce en dar araç kullanılır. `remember`
// değeri yalnız recomposition boyunca saklar. Configuration change (rotation)
// ve process death sonrası kaybolur. Bunu bu aşamada kasıtlı yaşa; Aşama 2'de
// aynı state ViewModel'e taşınır.
// Tuzak: `remember`, `rememberSaveable` ve ViewModel aynı işi yapmaz.
// `rememberSaveable` Bundle'a sığan küçük değerleri configuration change ve
// process death boyunca geri getirir. Karmaşık nesne için Saver gerekir ve
// değer ekranlar arasında paylaşılmaz. ViewModel configuration change'i
// yaşatır, Navigation back stack entry'sine scope'lanır ve StateFlow tutar.
// Process death için Aşama 5'teki SavedStateHandle gerekir.
// Araştır: https://developer.android.com/develop/ui/compose/state
// Araştır: https://developer.android.com/develop/ui/compose/state-saving

// TODO [Aşama 1] Bu aşamada en az bir test yaz.
// Ne: `app/src/androidTest` altında bir Compose UI testi. `LoginScreen` ilk
// çizildiğinde beklediğin metin ya da düğme görünsün. `ExampleInstrumentedTest`
// bu testin yerine geçmez.
// Neden: elle tıklamak, bir sonraki aşamada alanın kaybolmasını yakalamaz.
// Araştır: https://developer.android.com/develop/ui/compose/testing

// TODO [Aşama 1] State değişimini Debugger ve Logcat ile izle.
// Ne: düğmeye basınca `remember` içindeki değerin yazıldığı satıra breakpoint
// koy. Logcat filtresinde paket `com.example.grokapp` olsun; tag'i sen seç.
// Neden: ekranda görünmemek ile state'in hiç yazılmaması farklı hatalardır.
// Araştır: https://developer.android.com/studio/debug
// Araştır: https://developer.android.com/studio/debug/logcat

// TODO [Aşama 1] Bu aşama bitince Karar notunu yaz: `docs/decisions/01-login-ui.md`.
// Ne seçildi (`remember` mı, `rememberSaveable` mı), neden, ve ViewModel
// alternatifi neden bu aşamada kullanılmadı.

@Composable
fun LoginScreen() {
    Text(text = "TODO")
}
