import com.google.gson.Gson
import com.theonlytails.loottables.*
import io.kotest.core.spec.style.StringSpec
import net.minecraft.block.BeehiveBlock
import net.minecraft.block.Blocks
import net.minecraft.loot.LootParameterSets.BLOCK
import net.minecraft.loot.LootSerializers
import net.minecraft.loot.functions.CopyNbt.Source.BLOCK_ENTITY
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val logger: Logger = LogManager.getLogger()

val gson: Gson = LootSerializers.createLootTableSerializer()
	.disableHtmlEscaping()
	.setPrettyPrinting()
	.create()

class LootTablesTest : StringSpec({
	"beehive loot table" {
		logger.info(gson.toJson(lootTable(BLOCK) {
			pool {
				alternativesEntry(
					itemEntry(Blocks.BEEHIVE, addToPool = false) {
						condition { hasSilkTouch() }
						function {
							copyNbt(BLOCK_ENTITY) {
								copy("Bees", "BlockEntityTag.Bees")
							}
						}
						function {
							copyState(Blocks.BEEHIVE) {
								copy(BeehiveBlock.HONEY_LEVEL)
							}
						}
					},

					itemEntry(Blocks.BEEHIVE, addToPool = false)
				)
			}
		}))
	}
}
)