import com.theonlytails.loottables.*
import io.kotest.core.spec.style.StringSpec
import net.minecraft.block.Blocks
import net.minecraft.block.SlabBlock
import net.minecraft.loot.LootParameterSets.BLOCK
import net.minecraft.state.properties.SlabType

class LootTablesTest : StringSpec({
	"drop slabs" {
		lootTable(BLOCK) {
			pool {
				itemEntry(Blocks.ACACIA_SLAB)

				condition { survivesExplosion() }
				function {
					setCount(constantRange(2)) {
						condition {
							blockStateProperty(Blocks.ACACIA_SLAB).setProperties(
								stateProperties { hasProperty(SlabBlock.TYPE, SlabType.DOUBLE) }
							)
						}
					}
				}
			}
		}
	}
}
)