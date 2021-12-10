
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.time.Instant.now
import java.time.format.DateTimeFormatter.ISO_INSTANT

plugins {
	idea
	`java-library`
	kotlin("jvm") version "1.6.0"
	id("net.minecraftforge.gradle") version "5.+"
	id("com.vanniktech.maven.publish.base")
	id("org.jetbrains.dokka") version "latest.release" // dokka
}

// Config -> Minecraft
val forgeVersion = findProperty("forge_version") as String
val minecraftVersion = findProperty("minecraft_version") as String
val projectVersion = findProperty("version") as String
val groupId = findProperty("groupId") as String
val modId = findProperty("modId") as String

// JVM Info
println("""
		Java: ${System.getProperty("java.version")} 
		JVM: ${System.getProperty("java.vm.version")} (${System.getProperty("java.vendor")}) 
		Arch: ${System.getProperty("os.arch")}
	""".trimIndent())

// Minecraft
minecraft {
	mappings("official", minecraftVersion)

	// @Suppress("SpellCheckingInspection")
	// accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

	runs {
		all {
			lazyToken("minecraft_classpath") {
				library.copyRecursive().resolve().joinToString(File.pathSeparator) { it.absolutePath }
			}
		}
	}
}

val library: Configuration by configurations.creating

configurations.implementation.get().extendsFrom(library)

repositories {
	mavenCentral()
}

dependencies {
	"minecraft"(group = "net.minecraftforge", name = "forge", version = "$minecraftVersion-$forgeVersion")
	library(group = "org.jetbrains.kotlin", name = "kotlin-stdlib", version = kotlin.coreLibrariesVersion) {
		exclude("org.jetbrains", "annotations")
	}
}

// Setup
project.group = groupId
project.version = projectVersion
base.archivesName.set(modId)

val javaVersion = 17

// Sets the toolchain to compile against Java 17
java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion))
	}
}
kotlin {
	jvmToolchain {
		(this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(javaVersion))
	}
}

tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "$javaVersion"
}

// Finalize the jar by re-obfuscating
tasks.named<Jar>("jar") {
	// Manifest
	manifest {
		attributes(
			"Specification-Title" to "LootGoblin",
			"Specification-Vendor" to "TheOnlyTails",
			"Specification-Version" to "1",
			"Implementation-Title" to "LootGoblin",
			"Implementation-Version" to project.version,
			"Implementation-Vendor" to "LootGoblin",
			"Implementation-Timestamp" to ISO_INSTANT.format(now()),
			"FMLModType" to "GAMELIBRARY",
		)
	}

	finalizedBy("reobfJar")
}

// Dokka
tasks.dokkaHtml.configure {
	outputDirectory.set(projectDir.resolve("docs"))
}

// Publishing to maven central
plugins.withId("com.vanniktech.maven.publish.base") {
	mavenPublishing {
		publishToMavenCentral(SonatypeHost.S01)
		signAllPublications()

		pom {
			name.set("LootGoblin")
			description.set("A Kotlin DSL for creating loot tables in Minecraft Forge mods.")
			url.set("https://github.com/theonlytails/lootgoblin")
			inceptionYear.set("2021")

			licenses {
				license {
					name.set("MIT License")
					url.set("https://www.opensource.org/licenses/mit-license.php")
					distribution.set("repo")
				}
			}

			scm {
				connection.set("scm:git:git://github.com/theonlytails/lootgoblin.git")
				developerConnection.set("scm:git:ssh://git@github.com/theonlytails/lootgoblin.git")
				url.set("https://github.com/theonlytails/lootgoblin/")
			}

			developers {
				developer {
					name.set("TheOnlyTails")
					id.set("theonlytails")
					url.set("https://github.com/theonlytails/")
				}
			}

			withXml {
				val document = asElement()
				val dependencies = document.getElementsByTagName("dependencies")

				fun NodeList.forEach(action: Node.() -> Unit) {
					for (i in 0 until length) {
						item(i).action()
					}
				}

				dependencies.forEach {
					asElement().getElementsByTagName("version").forEach {
						textContent = textContent.removeSuffix("_mapped_official_$minecraftVersion")
					}
				}
			}
		}
	}
}

publishing.publications.create<MavenPublication>("mavenLocal") {
	artifactId = modId
}