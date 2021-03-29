[![](https://jitpack.io/v/TheOnlyTails/LootTables.svg)](https://jitpack.io/#TheOnlyTails/LootTables)

# LootTables

A Kotlin DSL for creating loot tables in Minecraft Forge mods.

## Installation

#### Gradle/Groovy:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation fg.deobf(project.dependencies.create(group: "com.github.TheOnlyTails", name: "LootTables", version: version) {
	transitive = false
    })
}
```

#### Gradle/Kotlin:
```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(project.the<DependencyManagementExtension>()
	.deobf(project.dependencies.create(group = "com.github.TheOnlyTails", name = "LootTables", version = version)
		.apply {
			isTransitive = false
		}
	))
}
```
