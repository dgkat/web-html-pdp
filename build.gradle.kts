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
                sourceMaps = true
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

                // Koin for Dependency Injection
                implementation("io.insert-koin:koin-core:3.5.6")

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
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
                implementation("com.juul.indexeddb:core:0.9.0")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.550")            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("")
}

tasks.register<Delete>("cleanDocs") {
    delete("docs")
}

tasks.register<Copy>("prepareForGitHubPages") {
    // Ensure the project builds before copying
    dependsOn("jsBrowserProductionWebpack")

    // Clear old docs directory before copying
    dependsOn("cleanDocs")

    // Copy HTML and CSS from resources to docs
    from("src/jsMain/resources") {
        include("index.html", "styles.css")
    }
    into("docs/")

    // Copy JS and map files from the production output to docs
    from("build/kotlin-webpack/js/productionExecutable") {
        include("app.bundle.js", "app.bundle.js.map")
    }
    into("docs/")
}

tasks.register("deployToGitHubPages") {
    dependsOn("prepareForGitHubPages")

    doLast {
        println("Deployment files are ready. Commit and push the changes to GitHub.")
    }
}
