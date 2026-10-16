# GrokApp

Öğrenme projesi. Paket `com.example.grokapp`. `compileSdk` ve `targetSdk` 36, `minSdk` 24. Plugin'ler şimdilik yalnız `android.application` ve `kotlin.compose`.

Gerçek kodu **mert** yazar. Bu depodaki sınıflar boştur: derlensin diye paket satırı, en küçük bildirim ve `Text("TODO")` vardır. Gövde yoktur. `TODO [Aşama N]` yorumu ne yapılacağını, neden yapılacağını ve hangi sayfanın okunacağını söyler. Çözümü yazmaz.

## Amaç ve çalışma biçimi

mert aşamayı sırayla, AI kullanmadan ilerletir.

1. Aşağıdaki aşamayı ve o aşamanın `TODO [Aşama N]` yorumlarını oku.
2. Kodu kendin yaz. Bir aşamanın testini de o aşamada yaz. Testi sona bırakma.
3. `docs/decisions/` altına Karar notunu yaz. Şablon `docs/decisions/README.md` içindedir: ne seçildi, neden, alternatif.
4. Dalı push et.
5. Koç diff'i ve Karar notunu inceler. Yeşil test, eksik notun yerine geçmez.

Tek Gradle modülü (`:app`) kasıtlıdır. Paketler (`ui`, `navigation`, `data`, `model`, `di`) ilerideki modül sınırıdır. Sınır oturmadan modül açmak döngüsel bağımlılık üretir. Bölme Aşama 8'dedir.

Hilt, KSP ve Kotlin serialization bağımlılıkları yoktur. Version catalog'a onları eklemek Aşama 1'in aracıyla, ilgili aşamada mert'in işidir. Hazır sürüm bu depoda durmaz.

## Mimari

Akış tek yönlüdür. Ekran olay gönderir, ViewModel state üretir, Screen state'i çizer.

| Parça | Nerede | Neden duruyor |
| --- | --- | --- |
| `LoginScreen` | `ui/login` | Aşama 1'de state `remember` ile burada yaşar. |
| `LoginViewModel`, `LoginUiState` | `ui/login` | Configuration change'i ViewModel yaşatır. StateFlow son değeri yeni collector'a verir. |
| `LoginEvent` | `ui/login` | Navigasyon bir kez tüketilir. State'e bayrak olarak konursa rotation'da tekrar oynar. |
| `HomeRoute` | `ui/home` | Stateful giriş. ViewModel'i alır, state'i toplar. |
| `HomeScreen` | `ui/home` | Stateless çizim. ViewModel görmez; Preview ve Compose testi burayı çağırır. |
| `HomeViewModel`, `HomeUiState` | `ui/home` | Paralel yükleme ve process death burada toplanır. |
| `AppNavHost`, `Routes` | `navigation` | Graf ve type-safe route. `MainActivity` hedef bilmez. |
| `HomeRepository` | `data` | Sözleşme. ViewModel Fake mi Room mu bilmez. |
| `FakeHomeRepository` | `data` | Aşama 4 ve 5'in impl'i. Ağ yok. Süre kanıtı burada kurulur. |
| `Profile`, `Announcement` | `model` | UI'ın gördüğü tip. Paket `dto` değildir. Ağ ve tablo şeması Aşama 6'da ayrı tip olur, mapper bu sınıflara çevirir. |
| `RepositoryModule` | `di` | İleride Hilt `@Binds`. Annotation bugün yok; yazılırsa derleme kırılır. |

`LoginUiState` ve `HomeUiState` sealed olacak. `when` exhaustive kalır, `else` yazılmaz. `else`, yeni alt tip eklenince derleyicinin işaret ettiği eksiği yutar.

Route/Screen ayrımı, Navigation ile çizimi ayırır. `HomeRoute` stateful, `HomeScreen` stateless.

Repository arayüzü ile impl ayrımı, testi ağa bağlamaz. DI, Activity'nin `FakeHomeRepository()` kurmasını engeller. Bağlama Aşama 4'te öğrenilir; bu scaffold bağlamaz.

## Aşamalar

Her aşamada en az bir test vardır. Aşama 7, testin derinleştiği aşamadır. Her aşama bir Karar notu ile biter.

### [ ] Aşama 1 — Login UI, `remember`

Login ekranını ViewModel'siz kur. State `remember` içinde kalsın.

`remember` yalnız recomposition'ı yaşatır. `rememberSaveable` Bundle'a sığan değeri configuration change ve process death boyunca geri getirir; ekranlar arasında paylaşılmaz. ViewModel configuration change'i yaşatır. Farkı rotation ile gör, sonra Aşama 2'ye geç.

- Araç: Debugger, breakpoint, Logcat filtresi (`com.example.grokapp`), version catalog.
- Test: `LoginScreen` için bir Compose UI testi. `ExampleInstrumentedTest` sayılmaz.
- Not: `docs/decisions/01-login-ui.md`
- Sayfalar: [state](https://developer.android.com/develop/ui/compose/state), [state-saving](https://developer.android.com/develop/ui/compose/state-saving), [debug](https://developer.android.com/studio/debug), [logcat](https://developer.android.com/studio/debug/logcat), [version catalog](https://developer.android.com/build/migrate-to-catalogs)

### [ ] Aşama 2 — ViewModel, StateFlow, UiState

`remember` state'ini `LoginViewModel` içindeki `StateFlow`'a taşı. `LoginUiState` sealed olsun. `when` içinde `else` olmasın.

- Araç: Layout Inspector recomposition sayısı, stable / unstable parametre.
- Test: `LoginViewModel` için local unit test. `ExampleUnitTest` sayılmaz.
- Not: `docs/decisions/02-viewmodel-uistate.md`
- Skill (reviewer, kodu yazdıktan sonra): `jetpack-compose-performance`
- Sayfalar: [stateholders](https://developer.android.com/topic/architecture/ui-layer/stateholders), [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow), [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Layout Inspector](https://developer.android.com/studio/debug/layout-inspector), [stability](https://developer.android.com/develop/ui/compose/performance/stability)

### [ ] Aşama 3 — Navigation ve tek seferlik event

`AppNavHost` login'den başlasın. Başarıda home'a geç. `popUpTo` içinde `inclusive = true` kullan. `inclusive = false` login'i stack'te bırakır; geri tuşu forma döner.

One-shot navigation için `Channel` kapasitesi `BUFFERED`, dışarı akış `receiveAsFlow()`. `SharedFlow(replay = 0)` geçmişi tutmaz; emit anında collector yoksa event düşer. `replay = 1` ise rotation'dan sonra aynı navigasyon tekrar oynar. `Channel` event'i buffer'da tutar ve bir kez teslim eder.

Resmi events sayfası bazı olayları state olarak da anlatır. Kodu Channel ile kur, alternatifi Karar notuna yaz.

Route tipleri type-safe olacak. `@Serializable` ancak serialization plugin'i senden sonra eklenince derlenir. Navigation 3 (`NavKey`, `NavDisplay`) ayrı bir API'dir; bu aşamanın sayfası type-safe route sayfasıdır. Seçimi nota yaz.

`HomeRoute` / `HomeScreen` ayrımı bu aşamada bağlanır. Toplama Aşama 5'te `collectAsStateWithLifecycle` olur.

- Araç: LeakCanary (kurulumunu kendi dokümanından doğrula), Memory Profiler.
- Test: başarı event'i bir kez teslim edilsin; ikinci collector aynı event'i görmesin.
- Not: `docs/decisions/03-navigation-events.md`
- Sayfalar: [Navigation](https://developer.android.com/guide/navigation), [back stack](https://developer.android.com/guide/navigation/backstack), [type-safe routes](https://developer.android.com/guide/navigation/design/type-safety), [Navigation 3](https://developer.android.com/guide/navigation/navigation-3), [events](https://developer.android.com/topic/architecture/ui-layer/events), [navigation testing](https://developer.android.com/guide/navigation/testing), [Memory Profiler](https://developer.android.com/studio/profile/memory-profiler)

### [ ] Aşama 4 — Repository ve Hilt

`HomeRepository` sözleşme, `FakeHomeRepository` impl. `RepositoryModule` içinde `@Binds`. Hilt ve KSP'yi catalog'a sen eklersin. `kapt` kopyalama. AGP 9 built-in Kotlin kullanır; plugin satırını sürüm notuyla karşılaştır.

Hassas küçük veri: DataStore ve Android Keystore'u oku. Jetpack Security `EncryptedSharedPreferences` deprecated. Cryptography sayfası, `security-crypto` API'lerinin 1.1.0 kararlı sürümünde deprecated olduğunu yazar. Sonraki sürüm yoktur. Bunu sayfadan doğrula. DataStore'un güncel şifreleme seçeneğini de aynı okumada kontrol et.

- Araç: KSP, Build Analyzer, DataStore + Keystore, Detekt / ktlint / Android Lint. Detekt ve ktlint resmi Android aracı değildir; Lint sayfasını esas al, diğerini Karar notunda alternatif yaz.
- Test: fake, sözleşmenin başarılı yolunu ağsız döner. Delay şartı Aşama 5'tedir.
- Not: `docs/decisions/04-repository-hilt.md`
- Sayfalar: [data layer](https://developer.android.com/topic/architecture/data-layer), [Hilt](https://developer.android.com/training/dependency-injection/hilt-android), [KSP](https://developer.android.com/build/migrate-to-ksp), [AGP 9](https://developer.android.com/build/releases/agp-9-0-0-release-notes), [Build Analyzer](https://developer.android.com/studio/build/build-analyzer), [Lint](https://developer.android.com/studio/write/lint), [DataStore](https://developer.android.com/topic/libraries/architecture/datastore), [Keystore](https://developer.android.com/privacy-and-security/keystore), [Cryptography](https://developer.android.com/privacy-and-security/cryptography), [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences)

### [ ] Aşama 5 — Home, paralel yükleme, SavedStateHandle

Profil 1500 ms, duyurular 1000 ms. Paralel bitiş yaklaşık 1500 ms'dir. Sıralı bekleme yaklaşık 2500 ms'dir ve yanlıştır. Kullanıcı adı tam olarak `"error"` ise yükleme hata fırlatır.

`CancellationException` `Exception` altındadır. Genel `catch` onu yutar, iptal bozulur, `viewModelScope` temizlenmez. İptal, hata state'i değildir. Yakalanırsa yeniden fırlat; daha iyisi yalnız beklediğin tipi yakala.

State'i `collectAsStateWithLifecycle` ile topla. `collectAsState` lifecycle `STOPPED` iken de toplamaya devam eder.

`LazyColumn` öğesine stabil `key` ver. `key` index olmasın.

`SavedStateHandle` process death içindir. Rotation'ı ViewModel zaten yaşatır. Handle constructor'dan gelir.

- Araç: CPU Profiler, System Trace, App Inspection. App Inspection penceresinin sekmeleri Network Inspector ve Database Inspector'dır. Bu aşamada ağ yoktur; sekmelerin boş kalması beklenen sonuçtur.
- Test: `runTest` ile sanal zaman. Paralel yol yaklaşık 1500 ms, `"error"` hata üretir, iptal hata state'ine yazılmaz. Gerçek `delay` ile 1.5 saniye uyuma.
- Not: `docs/decisions/05-home-async-savedstate.md`
- Skill (reviewer): `android-profiler`
- Sayfalar: [coroutine'ler](https://developer.android.com/kotlin/coroutines/coroutines-best-practices), [coroutine test](https://developer.android.com/kotlin/coroutines/test), [SavedStateHandle](https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate), [listeler](https://developer.android.com/develop/ui/compose/lists), [lifecycle-aware flow](https://developer.android.com/topic/libraries/architecture/coroutines), [CPU Profiler](https://developer.android.com/studio/profile/cpu-profiler), [system tracing](https://developer.android.com/topic/performance/tracing), [Performance Analyzer](https://developer.android.com/android-performance-analyzer)

### [ ] Aşama 6 — Gerçek data katmanı

Retrofit ile ağ, hata modeli (`Result` veya sealed error; birini seç), Room, offline-first önbellek. Okumanın kaynağı yerel depodur; ağ onu günceller.

`HomeRepository` HTTP yanıtı, Entity veya DTO döndürmez. `model` paketi UI tipidir. Şema ayrı tipe konur ve mapper `Profile` / `Announcement` üretir.

- Araç: Network Inspector, Database Inspector.
- Test: hata eşlemesi için local unit test, ya da Room DAO için in-memory instrumented test. En az biri.
- Not: `docs/decisions/06-data-layer.md`
- Sayfalar: [offline-first](https://developer.android.com/topic/architecture/data-layer/offline-first), [Room](https://developer.android.com/training/data-storage/room), [data layer](https://developer.android.com/topic/architecture/data-layer), [Network Inspector](https://developer.android.com/studio/debug/network-profiler), [Database Inspector](https://developer.android.com/studio/inspect/database)

### [ ] Aşama 7 — Derin test

Aşama 1–6'daki testler durur. Bu aşama onları derinleştirir: akışın Compose UI testi, Macrobenchmark, Baseline Profile.

Macrobenchmark ve Baseline Profile resmi sayfada ayrı test modülündedir. O modül Aşama 8'deki feature bölünmesi değildir.

Crashlytics Firebase ürünüdür. Android vitals Play Console verisidir. İkisini de oku; Crashlytics'i developer.android.com sayfası sanma.

- Araç: Compose UI test, Macrobenchmark, Baseline Profile, Crashlytics, Android vitals.
- Test: en az bir Macrobenchmark veya bir Baseline Profile jeneratörü. Buna ek olarak login'den home'a giden bir Compose UI testi.
- Not: `docs/decisions/07-deep-testing.md`
- Skill (reviewer): `testing-setup`
- Sayfalar: [Compose testing](https://developer.android.com/develop/ui/compose/testing), [local tests](https://developer.android.com/training/testing/local-tests), [Macrobenchmark](https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview), [Baseline Profile](https://developer.android.com/topic/performance/baselineprofiles/overview), [Android vitals](https://developer.android.com/topic/performance/vitals)

### [ ] Aşama 8 — Multi-module

`core`, `feature-login`, `feature-home`, convention plugin. Feature modülü diğer feature'ın impl'ine değil, `core` sözleşmesine bağlanır.

- Araç: R8 / shrinking, build type ve product flavor, signing, APK ve AAB, Play App Signing ve upload key, `assembleRelease` ve `bundleRelease`, GitHub Actions, Play internal testing track.
- `assembleRelease` APK üretir. `bundleRelease` AAB üretir. Play App Signing'de upload key senin imzaladığın anahtardır; dağıtım anahtarını Play tutar.
- CI, [komut satırı derlemesi](https://developer.android.com/studio/build/building-cmdline) sayfasındaki Gradle görevini çalıştırır. Görev `assembleDebug`; yayın için `bundleRelease`.
- R8 keep kurallarını geniş yazma. `r8-analyzer` gereksiz ve fazla geniş kuralları işaret eder. Prompt: `Analyze the R8 configuration`. Analyzer sürüm şartını sayfadan doğrula; bu proje AGP 9.0.1'dedir.
- Test: Aşama 2 unit testi `feature-login` içinde geçmeye devam etsin. `core` içinde ek bir unit test yaz.
- Not: `docs/decisions/08-multi-module.md`
- Skill (reviewer): `r8-analyzer`
- Sayfalar: [modularization](https://developer.android.com/topic/modularization), [patterns](https://developer.android.com/topic/modularization/patterns), [R8](https://developer.android.com/topic/performance/app-optimization/enable-app-optimization), [R8 Configuration Analyzer](https://developer.android.com/topic/performance/app-optimization/r8-configuration-analyzer), [build variants](https://developer.android.com/build/build-variants), [app signing](https://developer.android.com/studio/publish/app-signing), [AAB](https://developer.android.com/guide/app-bundle), [publish](https://developer.android.com/studio/publish)

### [ ] Aşama 9 — İkinci feature

Arama, sayfalama ve form doğrulama. Multi-module bittikten sonra gelir. Modülü şimdi açma.

- Konu: Paging, form state, liste.
- Test: form kuralı için local unit test veya sayfa yüklemesi için bir test. En az biri.
- Not: `docs/decisions/09-second-feature.md`
- Sayfalar: [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview), [user input](https://developer.android.com/develop/ui/compose/text/user-input), [lists](https://developer.android.com/develop/ui/compose/lists)

## AI ile çalışmak

Kural: aşamayı önce elle yaz. Sonra eşleşen skill'i reviewer olarak çalıştır. Ürettiği kodu alma. Çıktıyı developer.android.com ile karşılaştır.

Android CLI skill kurar:

```text
android skills add [--agent=PARAM] [--all] [--project=PARAM] [<skill>]
```

Komutun sayfası: [android skills add](https://developer.android.com/tools/agents/android-cli/commands/skills_add).

Yüklü ve mevcut skill'ler: `android skills list`. Sayfa: [Android skills](https://developer.android.com/tools/agents/android-skills).

Depo: [android/skills](https://github.com/android/skills).

Bu yol haritasıyla ilgili skill'ler:

| Skill | Ne zaman |
| --- | --- |
| `jetpack-compose-performance` | Aşama 2, reviewer |
| `android-profiler` | Aşama 5, reviewer |
| `testing-setup` | Aşama 7, reviewer |
| `r8-analyzer` | Aşama 8, reviewer. Keep kurallarında gereksiz ve fazla geniş olanları arar. Prompt: `Analyze the R8 configuration`. [R8 Configuration Analyzer](https://developer.android.com/topic/performance/app-optimization/r8-configuration-analyzer) |
| `navigation-3` | Aşama 3 Karar notu için karşılaştırma. Kodu yapıştırma. |
| `edge-to-edge` | `MainActivity` zaten `enableEdgeToEdge` çağırır. Inset taşmasını reviewer olarak kontrol et. |
| `agp-9-upgrade` | Plugin eklemeden önce built-in Kotlin düzenini anlamak için. |
| `android-intent-security` | Exported bileşen ve Intent eklenirse reviewer. |
| `android-permissions-security` | İzin eklenirse reviewer. |

Gemini in Android Studio, kökteki `AGENTS.md` dosyasını okur: [agent files](https://developer.android.com/studio/gemini/agent-files). Project Rules `/.idea/project.prompts.xml` içindedir. `.gitignore` bütün `/.idea/` klasörünü yok sayar, bu yüzden Rules commit edilemez. Paylaşılacak kural `AGENTS.md` içindedir.

[Journeys](https://developer.android.com/studio/gemini/journeys) çok adımlı akışı kaydeder. [MCP](https://developer.android.com/studio/gemini/add-mcp-server) dış aracı Gemini'ye bağlar. İkisi de aşamayı mert'in yerine yazmaz.

## Derleme

`MainActivity` template `Greeting` ile durur. `AppNavHost`'u Aşama 3'te sen bağlarsın. Boş sınıflar ve placeholder composable'lar `./gradlew assembleDebug` ile derlenir. Yeni bağımlılık eklemeden `@HiltAndroidApp`, `@Serializable` veya `NavHost` yazma; classpath'te yoktur.

`core-ktx` 1.18.0 ve `lifecycle-runtime-ktx` 2.10.0 catalog'da durur. 1.19.0 ve 2.11.0, `compileSdk` 37 ile AGP 9.1.0 ister. Bu proje `compileSdk` 36 ve AGP 9.0.1'de kalır.
