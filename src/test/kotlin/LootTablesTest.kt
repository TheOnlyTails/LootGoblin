
import com.google.gson.Gson
import com.theonlytails.loottables.*
import io.kotest.core.spec.style.StringSpec
import net.minecraft.block.ShulkerBoxBlock
import net.minecraft.item.Items
import net.minecraft.loot.LootParameterSets.BLOCK
import net.minecraft.loot.LootSerializers
import net.minecraft.tags.ItemTags
import net.minecraft.util.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val logger: Logger = LogManager.getLogger()

val gson: Gson = LootSerializers.createLootTableSerializer()
	.disableHtmlEscaping()
	.setPrettyPrinting()
	.create()

class LootTablesTest : StringSpec({
	"loot tables and pools example + basic entry" {
		gson.toJson(lootTable(BLOCK) {
			pool {
				itemEntry(Items.STICK)
			}
		}).also(logger::info)
	}

	"loot entries" {
		gson.toJson(lootTable(BLOCK) {
			pool {
				itemEntry(Items.STICK /* this is an example item, of course */)
				tagEntry(ItemTags.PLANKS)
				tableEntry(ResourceLocation("grass_block"))
				alternativesEntry(itemEntry(Items.STICK, addToPool = false))
				dynamicEntry(ShulkerBoxBlock.CONTENTS) // I couldn't find any other example of this being used in vanilla.
				emptyEntry()
			}
		}).also(logger::info)
	}

	"loot conditions"{
		gson.toJson(lootTable(BLOCK) {
			pool {
				itemEntry(Items.STICK) {
					condition { randomChance(0.1f) }
				}

				condition { survivesExplosion() }
			}
		}).also(logger::info)
	}

	"loot functions" {
		gson.toJson(lootTable(BLOCK) {
			pool {
				itemEntry(Items.STICK) {
					function { explosionDecay() }
				}

				function { setConstantCount(2) } // this is a shortened form of setCount(constantCount(2))
			}
		}).also(logger::info)
	}
}
)