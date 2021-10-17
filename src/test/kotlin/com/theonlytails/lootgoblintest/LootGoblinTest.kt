package com.theonlytails.lootgoblintest

import com.theonlytails.lootgoblin.*
import com.theonlytails.lootgoblin.generation.DataGoblinEvent
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.Items.DIAMOND
import net.minecraft.world.item.Items.DIAMOND_ORE
import net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE
import net.minecraft.world.level.block.ShulkerBoxBlock
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.BLOCK
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

const val MOD_ID = "lootgoblin_test"

@Mod(MOD_ID)
// @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class LootGoblinTests {
	init {
		val modEventBus: IEventBus = FMLJavaModLoadingContext.get().modEventBus

		modEventBus.addListener<DataGoblinEvent> {
			it {
				lootTable(BLOCK) {
					pool {
						itemEntry(Items.STICK)
					}
				}

				lootTable(BLOCK) {
					pool {
						itemEntry(Items.STICK /* this is an example item, of course */)
						tagEntry(ItemTags.PLANKS)
						tableEntry(ResourceLocation("grass_block"))
						alternativesEntry(itemEntry(Items.STICK, addToPool = false))
						dynamicEntry(ShulkerBoxBlock.CONTENTS) // I couldn't find any other example of this being used in vanilla.
						emptyEntry(weight = 2, quality = 2)
					}
				}

				lootTable(BLOCK) {
					pool {
						itemEntry(Items.STICK) {
							condition { randomChance(0.1f) }
						}

						condition { survivesExplosion() }
					}
				}

				// this is a shortened form of setCount(constantCount(2))
				lootTable(BLOCK) {
					pool {
						itemEntry(Items.STICK) {
							function { explosionDecay() }
						}

						function { setConstantCount(2f) } // this is a shortened form of setCount(constantCount(2))
					}
				}

				lootTable(BLOCK) {
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
}