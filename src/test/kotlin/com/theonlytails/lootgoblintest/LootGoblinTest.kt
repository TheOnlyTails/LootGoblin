package com.theonlytails.lootgoblintest

import com.google.gson.JsonIOException
import com.theonlytails.lootgoblin.*
import net.minecraft.data.DataGenerator
import net.minecraft.data.HashCache
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.Items.DIAMOND
import net.minecraft.world.item.Items.DIAMOND_ORE
import net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE
import net.minecraft.world.level.block.ShulkerBoxBlock
import net.minecraft.world.level.storage.loot.Deserializers
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.BLOCK
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.LoggingException

val logger = LogManager.getLogger("LootGoblinTester") ?: throw LoggingException("error creating the logger")

val gson = Deserializers.createLootTableSerializer()
	.disableHtmlEscaping()
	.setPrettyPrinting()
	.create() ?: throw JsonIOException("error creating a Gson object")

fun testLootTable(testName: String, lootTable: LootTable.Builder.() -> LootTable.Builder) =
	lootTable(BLOCK, lootTable).also {
		logger.info("""
			Test: $testName
			${gson.toJson(it)}
		""".trimIndent())
	}

const val MOD_ID = "lootgoblin_test"

@Mod(MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class LootGoblinTests {
	companion object {
		@JvmStatic
		@SubscribeEvent
		fun gatherData(event: GatherDataEvent) {
			val gen = event.generator
			if (event.includeServer()) {
				gen.addProvider(LootTables(gen))
			}
		}
	}

	class LootTables(generator: DataGenerator) : LootTableProvider(generator) {
		override fun run(cache: HashCache) {
			testLootTable("loot tables and pools + basic entry") {
				pool {
					itemEntry(Items.STICK)
				}
			}

			testLootTable("loot entries") {
				pool {
					itemEntry(Items.STICK /* this is an example item, of course */)
					tagEntry(ItemTags.PLANKS)
					tableEntry(ResourceLocation("grass_block"))
					alternativesEntry(itemEntry(Items.STICK, addToPool = false))
					dynamicEntry(ShulkerBoxBlock.CONTENTS) // I couldn't find any other example of this being used in vanilla.
					emptyEntry(weight = 2, quality = 2)
				}
			}

			testLootTable("loot conditions") {
				pool {
					itemEntry(Items.STICK) {
						condition { randomChance(0.1f) }
					}

					condition { survivesExplosion() }
				}
			}

			testLootTable("loot functions") {
				pool {
					itemEntry(Items.STICK) {
						function { explosionDecay() }
					}

					function { setConstantCount(2f) } // this is a shortened form of setCount(constantCount(2))
				}
			}

			testLootTable("diamond ore") {
				pool {
					alternativesEntry(
						itemEntry(item = DIAMOND_ORE, addToPool = false) {
							condition { hasSilkTouch }
						},
						itemEntry(item = DIAMOND, addToPool = false) {
							function { oreBonusCount(BLOCK_FORTUNE) }
							function { explosionDecay() }
						}
					)
				}
			}
		}
	}
}