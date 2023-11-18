plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm { }
    linuxX64 { }

    sourceSets {
        val coroutinesVersion: String by project
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))

                // transport models
                implementation(project(":resources-common"))
                implementation(project(":resources-api-v1"))
                implementation(project(":resources-api-v1-mappers"))

                // logging
                implementation(project(":resources-api-log"))
                implementation(project(":resources-api-log-mappers"))

                // Stubs
                implementation(project(":resources-stubs"))

                // Biz
                implementation(project(":resources-biz"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(project(":resources-api-v1"))
                implementation(project(":resources-api-v1-mappers"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val linuxX64Test by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
