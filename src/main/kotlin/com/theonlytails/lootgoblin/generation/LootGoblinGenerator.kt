package com.theonlytails.lootgoblin.generation

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.theonlytails.lootgoblin.LootGoblinDsl
import net.minecraft.data.DataGenerator
import net.minecraft.data.DataProvider
import net.minecraft.data.HashCache
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.LootTables
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException

@LootGoblinDsl
private val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()

@LootGoblinDsl
private val logger: Logger = LoggerFactory.getLogger("LootGoblin")

@LootGoblinDsl
internal class LootGoblinGenerator(
	@LootGoblinDsl private val generator: DataGenerator,
	@LootGoblinDsl private val userTables: Map<ResourceLocation, LootTable>,
) : LootTableProvider(generator) {
	override fun run(cache: HashCache) {
		val outputFolder = generator.outputFolder
		userTables.forEach { (key: ResourceLocation, lootTable: LootTable) ->
			val path = outputFolder.resolve("data/${key.namespace}/loot_tables/${key.path}.json")

			try {
				DataProvider.save(gson, cache, LootTables.serialize(lootTable), path)
			} catch (e: IOException) {
				logger.error("Couldn't write loot table {}", path, e as Any)
			}
		}
	}

	override fun getName() = "LootGoblin"
}