import Build_gradle.*

// BuildScript
buildscript {
	repositories {
		maven(url = "https://files.minecraftforge.net/maven")
		jcenter()
		mavenCentral()
	}

	dependencies {
		classpath(group = "net.minecraftforge.gradle", name = "ForgeGradle", version = "4.1.+")
		classpath(group = "org.jetbrains.kotlin", name = "kotlin-gradle-plugin", version = "1.4.32")
	}
}

// Config -> Minecraft
val forgeVersion: String by extra
val minecraftVersion: String by extra
val kffVersion: String by extra

// Config -> Run Config
val level: String by extra
val markers: String by extra

// Config -> Gradle/Maven
val mavenGroup: String by extra
val artifact: String by extra
val modVersion: String by extra
val author: String by extra
val ossrhUsername: String by extra
val ossrhPassword: String by extra

// Plugins
plugins {
	`java-library`
	`maven-publish`
	signing
	kotlin("jvm") version "1.4.32"
}

apply(plugin = "net.minecraftforge.gradle")

// JVM Info
println(
	"Java: ${System.getProperty("java.version")}" +
			" JVM: ${System.getProperty("java.vm.version")}(${System.getProperty("java.vendor")})" +
			" Arch: ${System.getProperty("os.arch")}"
)

repositories {
	maven {
		name = "kotlinforforge"
		url = uri("https://thedarkcolour.github.io/KotlinForForge/")
	}
}

// Minecraft Dependency
// Note: Due to the way kotlin gradle works we need to define the minecraft dependency before we configure Minecraft
dependencies {
	"minecraft"(group = "net.minecraftforge", name = "forge", version = "$minecraftVersion-$forgeVersion")

	implementation(group = "thedarkcolour", name = "kotlinforforge", version = kffVersion)
	testImplementation(group = "io.kotest", name = "kotest-runner-junit5", version = "4.4.3")
}

// Minecraft
minecraft {
	mappingChannel = "official"
	mappingVersion = minecraftVersion

	accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

	runs {
		config("client")

		config("server")

		config("data") {
			args(
				"--mod",
				artifact,
				"--all",
				"--output",
				file("src/generated/resources/"),
				"--existing",
				file("src/main/resources/")
			)
		}
	}
}

// Setup
project.group = mavenGroup
project.version = "$minecraftVersion-$modVersion"
base.archivesBaseName = artifact

// Java 8 Target
tasks.withType<JavaCompile> {
	sourceCompatibility = "1.8"
	targetCompatibility = "1.8"
}

// Finalize the jar by Reobf
tasks.named<Jar>("jar") { finalizedBy("reobfJar") }

// Manifest
tasks.withType<Jar> {
	manifest {
		attributes(
			"Specification-Title" to artifact,
			"Specification-Vendor" to author,
			"Specification-Version" to "1",
			"Implementation-Title" to project.name,
			"Implementation-Version" to project.version,
			"Implementation-Vendor" to author,
			"Implementation-Timestamp" to Date().format("yyyy-MM-dd'T'HH:mm:ssZ"),
			"FMLModType" to "LIBRARY"
		)
	}
}

// Publishing to maven central
publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = mavenGroup
			artifactId = artifact
			version = modVersion

			from(components["java"])

			pom {
				name.set("LootTables")
				description.set("A Kotlin DSL for creating loot tables in Minecraft Forge mods")
				url.set("https://github.com/theonlytails/loottables")

				properties.set(
					mapOf(
						"project.build.sourceEncoding" to "UTF-8"
					)
				)

				licenses {
					license {
						name.set("MIT License")
						url.set("https://www.opensource.org/licenses/mit-license.php")
						distribution.set("repo")
					}
				}

				developers {
					developer {
						id.set("theonlytails")
						name.set("TheOnlyTails")
					}
				}

				issueManagement {
					url.set("https://github.com/theonlytails/loottables/issues")
					system.set("GitHub Issues")
				}

				scm {
					connection.set("scm:git:git:github.com/theonlytails/loottables.git")
					developerConnection.set("scm:git:git@github.com:theonlytails/loottables.git")
					url.set("https://github.com/theonlytails/loottables")
				}
			}
		}
	}

	repositories {
		maven {
			name = "LootTables"
			url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")

			credentials {
				username = System.getenv("MAVEN_USERNAME")
				password = System.getenv("MAVEN_PASSWORD")
			}
		}
	}
}

java {
	withJavadocJar()
	withSourcesJar()
}

signing {
	useGpgCmd()
	sign(publishing.publications["maven"])
}

// Utilities
typealias Date = java.util.Date
typealias SimpleDateFormat = java.text.SimpleDateFormat

fun Date.format(format: String) = SimpleDateFormat(format).format(this)

typealias RunConfig = net.minecraftforge.gradle.common.util.RunConfig
typealias UserDevExtension = net.minecraftforge.gradle.userdev.UserDevExtension

typealias RunConfiguration = RunConfig.() -> Unit

fun minecraft(configuration: UserDevExtension.() -> Unit) =
	configuration(extensions.getByName("minecraft") as UserDevExtension)

fun NamedDomainObjectContainerScope<RunConfig>.config(name: String, additionalConfiguration: RunConfiguration = {}) {
	val runDirectory = project.file("run")
	val sourceSet = the<JavaPluginConvention>().sourceSets["main"]

	create(name) {
		workingDirectory(runDirectory)
		property("forge.logging.markers", markers)
		property("forge.logging.console.level", level)

		additionalConfiguration(this)

		mods { create(artifact) { source(sourceSet) } }
	}
}

// Testing
tasks.withType<Test> {
	useJUnitPlatform()
}