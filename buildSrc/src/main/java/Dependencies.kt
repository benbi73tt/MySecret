import org.gradle.api.JavaVersion

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val domain = ":domain"
    const val data = ":data"
}

object Versions {
    //Test
    const val jUnit = "4.12"
    const val junitExt = "1.1.5"
    const val runner = "1.2.0"
    const val espressoCore = "3.5.1"

    const val activity = "1.8.0"
    const val fragment = "1.6.1"

    //Kotlin
    const val AGP = "8.1.2"
    const val kotlin = "1.9.20"
    const val KSP = "1.9.20-1.0.14"
    const val core = "1.12.0"
    const val coroutines = "1.6.4"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.4.3"
    const val coroutinesAndroid = "1.4.3"

    //UIComponents
    const val appcompat = "1.6.1"
    const val material = "1.10.0"
    const val swiperefreshlayout = "1.1.0"
    const val constraintlayout = "2.1.4"
    const val vbpd = "1.5.9"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.11.0"
    const val adapterCoroutines = "0.9.2"

    //Hilt
    const val daggerHilt = "2.48.1"

    //Coil
    const val coil = "0.11.0"

    //Room
    const val roomKtx = "2.5.2"
    const val runtime = "2.5.2"
    const val roomCompiler = "2.5.2"


    const val navigation = "2.7.4"
    const val lifecycle = "2.6.2"

    //DataStore
    const val datastore = "1.0.0"
    const val protobuf = "3.19.6"

    const val moshi = "1.15.0"
}

object Test {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object UIComponents {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val viewBindingPropertyDelegate =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.vbpd}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Core {
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val okhttp =
        "com.squareup.okhttp3:okhttp:${Versions.interceptor}"

}

object Hilt {
    const val android = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object Activity {
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
}

object Fragment {
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
}

object Navigation {
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object Lifecycle {
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
}

object DataStore {
    const val datastore = "androidx.datastore:datastore:${Versions.datastore}"
    const val protobuf = "com.google.protobuf:protobuf-javalite:${Versions.protobuf}"
}

object Moshi {
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
}