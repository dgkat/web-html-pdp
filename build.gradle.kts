plugins {
    kotlin("multiplatform") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    application
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
    id("org.jetbrains.compose") version "1.7.0"
}

group = "me.dgkat"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}
@OptIn(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalDistributionDsl::class)
kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                outputFileName = "app.bundle.js" // optional, ensures a single output bundle
                cssSupport{
                    enabled.set(true)
                }
            }

            distribution{
                outputDirectory.file("$buildDir/distributions")
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

                // Koin for Dependency Injection
                implementation("io.insert-koin:koin-core:3.5.6")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.compose.web:web-core:1.7.0")
                implementation("org.jetbrains.compose.runtime:runtime:1.7.0")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("")
}

/*
tasks.register() {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}*/
/*
tasks.register("run", org.jetbrains.compose.experimental.dsl.webExtension()) {
    // Configuration for web browser run
}*/
