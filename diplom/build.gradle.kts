plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("plugin.spring")  version "1.9.0"
    kotlin("plugin.serialization")  version "1.9.0"
    kotlin("multiplatform") version "1.9.0"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.9.0"
    id ("org.jetbrains.kotlin.plugin.jpa") version "1.9.0"
   // id("org.jetbrains.kotlin.js") version "1.9.0"
    application
}

group = "me.ilya"
version = "1.0-SNAPSHOT"

allOpen {
    annotations("jakarta.persistence.Entity", "jakarta.persistence.MappedSuperclass", "jakarta.persistence.Embedabble")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                //implementation ("jakarta.platform:jakarta.jakartaee-api")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
                implementation("org.springframework.boot:spring-boot-starter")
                implementation("org.jetbrains.kotlin:kotlin-reflect")
                implementation("io.ktor:ktor-server-netty:2.0.2")
                implementation("io.ktor:ktor-server-html-builder-jvm:2.0.2")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")

                implementation("org.springframework.boot:spring-boot-starter-web")
                implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
                implementation("org.springframework.boot:spring-boot-starter-websocket")
                implementation("org.springframework.boot:spring-boot-starter-data-jpa")
                implementation("org.springframework.boot:spring-boot-starter-security")

                implementation("org.hibernate.validator:hibernate-validator")
                implementation("org.postgresql:postgresql")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
                implementation("org.jetbrains.kotlin:kotlin-reflect")

                implementation("com.vk.api:sdk:1.0.14")
            }
        }
        val jvmTest by getting{
            dependencies{
                implementation("org.springframework.boot:spring-boot-starter-test")
            }
        }
        val jsMain by getting {
            dependencies {


                implementation(npm("react", "18.2.0"))

                implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.430"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
                implementation(npm("react-player", "2.12.0"))
                implementation(npm("react-share", "4.4.1"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")

//                val kotlinWrappersVersion = "1.0.0-pre.620"
//                implementation(platform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-tanstack-react-table")
            }
        }
        val jsTest by getting
    }
}

kotlin {
    js(IR) {
        browser {
            webpackTask {
                output.libraryTarget = "umd"
            }
            binaries.executable()
        }
    }
}



application {
    mainClass.set("me.ilya.application.DemoApplicationKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}

tasks.register("runFrontend") {
    doLast {
        exec {
            workingDir = file("build/js/packages/diplimP")
            commandLine("node", "bundle.js")
        }
    }
}