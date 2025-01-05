import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

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
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                outputFileName = "product.bundle.js" // Unique name for the Product bundle
                cssSupport {
                    enabled.set(true)
                }
                sourceMaps = true
            }
            runTask {
                devServerProperty.set(
                    KotlinWebpackConfig.DevServer(
                        open = true, // Open browser automatically
                        static = mutableListOf("$buildDir/dist/js/developmentExecutable"),
                        port = 8080, // Change port for cartPage
                    )
                )
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":webShared")) // Link to webShared
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
        val jsMain by getting {
            dependsOn(commonMain)
        }
    }
}


application {
    mainClass.set("") // Adjust as necessary
}

tasks.register("runProductPage") {
    dependsOn("jsBrowserDevelopmentRun")
}

tasks.register<Copy>("copyProductionFiles") {
    group = "build"
    description = "Copies production build files to a single directory for serving."

    val customOutputDir = layout.buildDirectory.dir("dist/js/customExecutable")

    from(layout.buildDirectory.dir("kotlin-webpack/js/productionExecutable")) {
        include("product.bundle.js", "product.bundle.js.map")
    }

    from(layout.buildDirectory.dir("processedResources/js/main")) {
        include("index.html", "styles.css")
    }

    into(customOutputDir)

    doLast {
        println("✅ Production files copied to ${customOutputDir.get().asFile.absolutePath}")
    }
}

tasks.named("jsBrowserProductionWebpack") {
    finalizedBy("copyProductionFiles") // Ensure copying runs after webpack build
}