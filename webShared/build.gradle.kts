plugins {
    kotlin("multiplatform") version "2.0.21"
    kotlin("plugin.serialization") version "1.9.22"
    application
    id("com.google.devtools.ksp") version "2.0.21-1.0.28"
}

group = "me.dgkat"
version = "1.0-SNAPSHOT"

kotlin {
    js(IR) {
        browser()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                implementation("io.insert-koin:koin-core:3.5.6")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
                implementation("com.juul.indexeddb:core:0.9.0")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.550")
                implementation("org.jetbrains.compose.web:web-core:1.7.0")
                implementation("org.jetbrains.compose.runtime:runtime:1.7.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        // Ensure jsMain depends on commonMain
        val jsMain by getting {
            dependsOn(commonMain)
        }

        val jsTest by getting {
            dependsOn(commonTest)
        }
    }
}