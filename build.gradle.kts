group = "xyz.stavola.duration"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        mavenCentral()
        gradleScriptKotlin()
    }
    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("kotlin")
    plugin("maven")
}

repositories {
    mavenCentral()
}

val versions = mapOf(
        "kotlin" to "1.1.0",
        "junit" to "4.12"
)

dependencies {
    compile(kotlinModule("stdlib", versions["kotlin"]))

    subprojects.forEach {
        testCompile(it.buildDir)
    }

    testCompile(group="junit", name="junit", version=versions["junit"])
}
