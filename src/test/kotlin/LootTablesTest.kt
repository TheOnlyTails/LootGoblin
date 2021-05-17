import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.theonlytails.loottables.*
import net.minecraft.block.ShulkerBoxBlock
import net.minecraft.item.Items
import net.minecraft.loot.LootParameterSets.BLOCK
import net.minecraft.loot.LootSerializers
import net.minecraft.loot.LootTable
import net.minecraft.tags.ItemTags
import net.minecraft.util.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.LoggingException
import org.junit.jupiter.api.Test

val logger = LogManager.getLogger("LootTablesTester") ?: throw LoggingException("error creating the logger")

val gson = LootSerializers.createLootTableSerializer()
	.disableHtmlEscaping()
	.setPrettyPrinting()
	.create() ?: throw JsonIOException("error creating a Gson object")

fun Gson.testLootTable(lootTable: LootTable.Builder.() -> LootTable.Builder) =
	logger.info(this.toJson(lootTable(BLOCK, lootTable)))

class LootTablesTests {
	@Test
	fun `loot tables and pools example + basic entry`() = gson.testLootTable {
		pool {
			itemEntry(Items.STICK)
		}
	}

	@Test
	fun `loot entries`() = gson.testLootTable {
		pool {
			itemEntry(Items.STICK /* this is an example item, of course */)
			tagEntry(ItemTags.PLANKS)
			tableEntry(ResourceLocation("grass_block"))
			alternativesEntry(itemEntry(Items.STICK))
			dynamicEntry(ShulkerBoxBlock.CONTENTS) // I couldn't find any other example of this being used in vanilla.
			emptyEntry(weight = 2, quality = 2)
		}
	}

	@Test
	fun `loot conditions`() = gson.testLootTable {
		pool {
			itemEntry(Items.STICK) {
				condition { randomChance(0.1f) }
			}

			condition { survivesExplosion() }
		}
	}

	@Test
	fun `loot functions`() = gson.testLootTable {
		pool {
			itemEntry(Items.STICK) {
				function { explosionDecay() }
			}

			function { setConstantCount(2) } // this is a shortened form of setCount(constantCount(2))
		}
	}
}