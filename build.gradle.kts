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
}

dependencies {
    implementation(kotlin("stdlib"))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.19.0")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

jmh {
    warmupIterations.set(1)
    iterations.set(1)
    fork.set(1)
    benchmarkMode.set(listOf("AverageTime"))
    resultFormat.set("CSV")
    resultsFile.set(file("build/reports/benchmarks.csv"))
}
