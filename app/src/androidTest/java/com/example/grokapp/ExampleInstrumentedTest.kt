package com.example.grokapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * TODO [Aşama 1] Bu sınıfı aşama testi sayma. LoginScreen için ayrı bir
 * Compose UI testi yaz.
 * Araştır: https://developer.android.com/develop/ui/compose/testing
 *
 * TODO [Aşama 7] Derin test aşaması. Aşama 1'deki tek ekran testi yetmez.
 * Ne: kritik akış için Compose UI testi, Macrobenchmark ve Baseline Profile.
 * Macrobenchmark ve Baseline Profile ayrı modül ister; bağımlılığı app
 * modülüne gömme. Crashlytics Firebase ürünüdür, Android vitals Play Console
 * verisidir. İkisini de bu aşamada oku. En az bir benchmark veya Baseline
 * Profile jeneratörü bu aşamanın zorunlu testidir.
 * Araştır: https://developer.android.com/develop/ui/compose/testing
 * Araştır: https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview
 * Araştır: https://developer.android.com/topic/performance/baselineprofiles/overview
 * Araştır: https://developer.android.com/topic/performance/vitals
 *
 * TODO [Aşama 7] Aşama bitince Karar notu: `docs/decisions/07-deep-testing.md`.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.grokapp", appContext.packageName)
    }
}