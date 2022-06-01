import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform

fun getJavaFXPlatform(): String {
    val currentOS = DefaultNativePlatform.getCurrentOperatingSystem()
    if (currentOS.isWindows) {
        return "win"
    } else if (currentOS.isLinux) {
        return "linux"
    } else if (currentOS.isMacOsX) {
        return "mac"
    }
    throw IllegalStateException("Unexpected OS: ${currentOS.name}")
}

plugins {
    kotlin("jvm") version "1.6.10"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    `java-library`
    id("me.champeau.jmh") version "0.6.1"
}
group = "org.example"

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}


dependencies {
    implementation(kotlin("stdlib"))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.19.0")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("org.jetbrains.lets-plot:lets-plot-jfx:2.3.0")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.2.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.3.0")
    implementation("org.openjfx:javafx-base:18:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-swing:18:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-graphics:18:${getJavaFXPlatform()}")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

detekt {
    parallel = true
    autoCorrect = true
}

jmh {
    warmupIterations.set(1)
    iterations.set(1)
    fork.set(1)
    benchmarkMode.set(listOf("AverageTime"))
    resultFormat.set("CSV")
    resultsFile.set(file("build/reports/benchmarks.csv"))
}
