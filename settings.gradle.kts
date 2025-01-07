pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "web_kjs_fake_shop"

include(":webShared")
include(":webPages:productPage")
include(":webPages:cartPage")

project(":webShared").projectDir = file("webShared")
project(":webPages:productPage").projectDir = file("webPages/productPage")
project(":webPages:cartPage").projectDir = file("webPages/cartPage")