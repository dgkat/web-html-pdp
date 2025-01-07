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
            runTask {
                devServerProperty.set(
                    KotlinWebpackConfig.DevServer(
                        open = true, // Open browser automatically
                        static = mutableListOf("$buildDir/dist/js/productionExecutable"),
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
                implementation("org.jetbrains.compose.web:web-core:1.7.0")
                implementation("org.jetbrains.compose.runtime:runtime:1.7.0")
            }
        }
        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
            }
        }
    }
}


application {
    mainClass.set("") // Adjust as necessary
}

tasks.register("runCartPage") {
    dependsOn("jsBrowserDevelopmentRun")
}

tasks.register<Copy>("copyProductionFiles") {
    group = "build"
    description = "Copies production build files to a single directory for serving."

    val customOutputDir = layout.buildDirectory.dir("dist/js/customExecutable")

    from(layout.buildDirectory.dir("kotlin-webpack/js/productionExecutable")) {
        include("cart.bundle.js", "cart.bundle.js.map")
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

tasks.register<Copy>("copyToWebsite") {
    dependsOn("jsBrowserProductionWebpack")
    from("$buildDir/kotlin-webpack/js/productionExecutable") {
        include("cart.bundle.js", "cart.bundle.js.map")
    }
    from("$buildDir/processedResources/js/main") {
        include("index.html", "styles.css")
    }
    into("$rootDir/build/dist/website/cart") // Copy cart page inside /cart
}