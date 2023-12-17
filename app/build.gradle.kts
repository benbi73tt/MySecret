plugins {
    id(Plugins.AGP.application)
    kotlin(Plugins.Kotlin.android)

    // Navigation Safe Args
    id(Plugins.Navigation.safeArgs)

    // Hilt
    id(Plugins.Hilt.android)

    id(Plugins.KSP.ksp)
}

android {
    namespace = Namespaces.app
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "ru.home.mysecrets"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName(AndroidConfig.release) {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        getByName(AndroidConfig.debug) {
            applicationIdSuffix = ".${AndroidConfig.debug}"
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = Options.compileOptions
        targetCompatibility = Options.compileOptions
    }

    kotlinOptions {
        jvmTarget = Options.kotlinOptions
    }
}

dependencies {
    // Modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))

    // Coroutines
    implementation(Coroutines.android)

    // Kotlin
    implementation(Core.core)

    // Activity
    implementation(Activity.activity)

    // Fragment
    implementation(Fragment.fragment)

    //Coil
    implementation(Coil.coil)

    // UI Components
    implementation(UIComponents.material)
    implementation(UIComponents.appcompat)
    implementation(UIComponents.constraintlayout)
    implementation(UIComponents.viewBindingPropertyDelegate)

    //Livedata
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Hilt
    implementation(Hilt.android)
    ksp(Hilt.compiler)

    // Navigation
    implementation(Navigation.fragment)
    implementation(Navigation.ui)

    // Lifecycle
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.runtime)

    //Tests
    testImplementation(Test.junit)
    androidTestImplementation(Test.jUnitExt)
    androidTestImplementation(Test.espresso)

    implementation("androidx.annotation:annotation:1.7.1")

    implementation("com.google.crypto.tink:tink:1.11.0")
}
configurations {
    all {
        exclude(group = "com.google.protobuf", module = "protobuf-javalite")
    }
}
