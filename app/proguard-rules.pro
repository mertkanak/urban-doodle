# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# TODO [Aşama 8] Geniş -keep kuralı ekleme. R8 Configuration Analyzer gereksiz
# ve fazla geniş kuralları, bir kuralın diğerini yuttuğu durumları işaret eder.
# İnceleme prompt'u: "Analyze the R8 configuration".
# Araştır: https://developer.android.com/topic/performance/app-optimization/r8-configuration-analyzer
# Araştır: https://developer.android.com/topic/performance/app-optimization/enable-app-optimization