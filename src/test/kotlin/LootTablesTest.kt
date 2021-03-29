import com.google.gson.Gson
import com.theonlytails.loottables.*
import io.kotest.core.spec.style.StringSpec
import net.minecraft.item.Items
import net.minecraft.loot.LootParameterSets.BLOCK
import net.minecraft.loot.LootSerializers
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val logger: Logger = LogManager.getLogger()

val gson: Gson = LootSerializers.createLootTableSerializer()
	.disableHtmlEscaping()
	.setPrettyPrinting()
	.create()

class LootTablesTest : StringSpec({
	"loot entries with functions" {
		logger.info(gson.toJson(lootTable(BLOCK) {
			pool {
				itemEntry(Items.COAL)

				condition { survivesExplosion() }
			}
		}))
	}
}
)