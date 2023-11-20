plugins {
    kotlin(Plugins.Kotlin.jvm)
}

java {
    sourceCompatibility = Options.compileOptions
    targetCompatibility = Options.compileOptions
}

dependencies {
    // Javax Inject
    api("javax.inject:javax.inject:1")

    //Kotlin
    api(Coroutines.core)
}