plugins {
    kotlin("multiplatform") version "2.0.21"
    kotlin("plugin.serialization") version "1.9.22"
    application
    id("com.google.devtools.ksp") version "2.0.21-1.0.28"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
    id("org.jetbrains.compose") version "1.7.1"
}

group = "me.dgkat"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                outputFileName = "cart.bundle.js" // Unique name for the Cart bundle
                cssSupport {
                    enabled.set(true)
                }
                sourceMaps = true
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":webShared")) // Link to webShared
                implementation("org.jetbrains.compose.web:web-core:1.7.0")
                implementation("org.jetbrains.compose.runtime:runtime:1.7.0")
            }
        }
        val jsMain by getting {
            dependsOn(commonMain)
        }
    }
}


application {
    mainClass.set("jsMain/kotlin/cartPage/presentation/CartPageScreen.kt") // Adjust as necessary
}

tasks.register("runCartPage") {
    dependsOn("jsBrowserDevelopmentRun")
}