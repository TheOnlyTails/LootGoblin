import com.google.gson.Gson
import com.theonlytails.loottables.*
import io.kotest.core.spec.style.StringSpec
import net.minecraft.block.Blocks
import net.minecraft.item.Items
import net.minecraft.loot.LootParameterSets.BLOCK
import net.minecraft.loot.LootSerializers

val gson: Gson = LootSerializers.createLootTableSerializer()
	.disableHtmlEscaping()
	.setPrettyPrinting()
	.create()

class LootTablesTest : StringSpec({
	"loot entries with functions" {
		lootTable(BLOCK) {
			pool {
				alternativesEntry(
					itemEntry(Blocks.COAL_ORE) {
						condition { hasSilkTouch() }
					},
					itemEntry(Items.COAL),
				).add(this)
			}
		}
	}
}
)