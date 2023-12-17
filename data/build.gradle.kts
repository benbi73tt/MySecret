plugins {
    id(Plugins.AGP.library)
    kotlin(Plugins.Kotlin.android)

    // KSP
    id(Plugins.KSP.ksp)

    id(Plugins.Proto.proto) version "0.9.1"
}

android {
    namespace = Namespaces.data

    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
    }

    compileOptions {
        sourceCompatibility = Options.compileOptions
        targetCompatibility = Options.compileOptions
    }

    kotlinOptions {
        jvmTarget = Options.kotlinOptions
    }

    buildTypes {
        getByName(AndroidConfig.release) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8000/\"")
        }

        getByName(AndroidConfig.debug) {
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8000/\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.19.6"
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}


dependencies {
    //Module
    implementation(project(Modules.domain))

    //Retrofit 2
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converterMoshi)
    implementation(Retrofit.adapter_coroutines)
    implementation(Retrofit.logging_interceptor)
    implementation(Retrofit.okhttp)

    // Moshi
    implementation(Moshi.moshi)
    implementation(Moshi.kotlin)

    //Room
    api(Room.runtime)
    ksp(Room.compiler)
    implementation(Room.room_ktx)

    //DataStore
    implementation(DataStore.datastore)
    implementation(DataStore.protobuf)

    //Tests
    testImplementation(Test.junit)
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.espresso)
}