import com.google.gson.Gson
import com.theonlytails.loottables.*
import io.kotest.core.spec.style.StringSpec
import net.minecraft.block.Blocks
import net.minecraft.loot.LootParameterSets.BLOCK
import net.minecraft.loot.LootSerializers
import net.minecraft.loot.functions.CopyName

val gson: Gson = LootSerializers.createLootTableSerializer()
	.disableHtmlEscaping()
	.setPrettyPrinting()
	.create()

class LootTablesTest : StringSpec({
	"loot entries with functions" {
		println(gson.toJson(lootTable(BLOCK) {
			pool {
				itemEntry(Blocks.CHEST) {
					function { copyName(CopyName.Source.BLOCK_ENTITY) }
				}
				condition { survivesExplosion() }
			}
		}))
	}
}
)