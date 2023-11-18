plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
}

application {
    mainClass.set("ru.otus.otuskotlin.marketplace.app.kafka.MainKt")
}

dependencies {
    val kafkaVersion: String by project
    val coroutinesVersion: String by project
    val atomicfuVersion: String by project
    val logbackVersion: String by project
    val kotlinLoggingJvmVersion: String by project
    val serializationVersion: String by project

    implementation(kotlin("stdlib-common"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:atomicfu:$atomicfuVersion")

    // log
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingJvmVersion")

    implementation(project(":resources-app-common"))

    // transport models
    implementation(project(":resources-common"))
    implementation(project(":resources-api-v1"))
    implementation(project(":resources-api-v1-mappers"))

    // logic
    implementation(project(":resources-biz"))

    testImplementation(kotlin("test-junit"))
}