plugins { id("com.android.application") }
android {
    namespace = "com.example.catalogoitalikacrm"
    compileSdk = 34
    defaultConfig { applicationId = "com.example.catalogoitalikacrm"; minSdk = 24; targetSdk = 34; versionCode = 8; versionName = "8.0" }
}
dependencies { implementation("androidx.core:core:1.12.0") }
