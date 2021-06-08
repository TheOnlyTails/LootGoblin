![Maven metadata URL](https://img.shields.io/maven-metadata/v?color=blue&label=maven%20central&logo=gradle&metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fservice%2Flocal%2Frepositories%2Freleases%2Fcontent%2Fcom%2Ftheonlytails%2Floottables%2Fmaven-metadata.xml&style=flat-square)

# LootTables

A Kotlin DSL for creating loot tables in Minecraft Forge mods.

For documentation and usage instructions, please take a look at the [wiki](https://github.com/TheOnlyTails/LootTables/wiki).

Here's the [`maven-metadata.xml`](https://s01.oss.sonatype.org/service/local/repositories/releases/content/com/theonlytails/loottables/maven-metadata.xml) of this library.

## Installation

###### Don't forget to replace the VERSION key with the version in the top with the Maven Central badge at the top!

#### Gradle/Groovy

```groovy
repositories {
    mavenCentral()
}

dependencies {
    def lootTables = fg.deobf(project.dependencies.create(group: "com.theonlytails", name: "loottables", version: VERSION) {
	    transitive = false
    })
    
	implementation fg.deobf(lootTables)
}
```

#### Gradle/Kotlin
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    val lootTables = project.dependencies.create(group = "com.theonlytails", name = "loottables", version = VERSION)
		.apply { isTransitive = false }

	implementation(project.the<DependencyManagementExtension>().deobf(lootTables))
}
```

The `isTransitive` property is added to make sure the library is imported correctly.
