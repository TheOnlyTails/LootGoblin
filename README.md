[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.theonlytails/loottables/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.theonlytails/loottables)

# LootTables

A Kotlin DSL for creating loot tables in Minecraft Forge mods.

For documentation and usage instructions, please take a look at the [wiki](https://github.com/TheOnlyTails/LootTables/wiki).

## Installation

###### Don't forget to replace the VERSION key with the version in the top with the Maven Central badge at the top!

#### Gradle/Groovy

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation fg.deobf(project.dependencies.create(group: "com.theonlytails", name: "loottables", version: VERSION) {
	transitive = false
    })
}
```

#### Gradle/Kotlin
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation(project.the<DependencyManagementExtension>()
	.deobf(project.dependencies.create(group = "com.theonlytails", name = "loottables", version = VERSION)
		.apply {
			isTransitive = false
		}
	))
}
```
