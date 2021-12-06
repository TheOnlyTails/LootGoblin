rootProject.name = "LootGoblin"

pluginManagement {
	repositories {
		maven(url = uri("https://maven.minecraftforge.net/"))
		mavenCentral()
		gradlePluginPortal()
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.toString() == "net.minecraftforge.gradle")
				useModule("${requested.id}:ForgeGradle:${requested.version}")
			else if (requested.id.toString() == "com.vanniktech.maven.publish")
				useModule("com.vanniktech:gradle-maven-publish-plugin:${requested.version}")
		}
	}
}