package com.theonlytails.lootgoblin

import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider

/**
 * Adds a new [LootPool.Builder] to a [LootTable.Builder].
 *
 * @param rolls the number of rolls in this pool.
 */
@LootGoblinDsl
fun LootTable.Builder.pool(rolls: Float = 1f, bonusRolls: Float = 0f, body: LootPool.Builder.() -> Unit) =
	withPool(LootPool.lootPool()
		.setRolls(constantValue(rolls))
		.setBonusRolls(constantValue(bonusRolls))
		.also(body)
	) ?: throw LootTableCreationException("Something went wrong while adding a pool to a loot table")

/**
 * Adds a new [LootPool.Builder] to a [LootTable.Builder].
 *
 * @param rolls the number of rolls in this pool.
 */
@LootGoblinDsl
fun LootTable.Builder.pool(rolls: NumberProvider, bonusRolls: NumberProvider, body: LootPool.Builder.() -> Unit) =
	withPool(LootPool.lootPool()
		.setRolls(rolls)
		.setBonusRolls(bonusRolls)
		.also(body)
	) ?: throw LootTableCreationException("Something went wrong while adding a pool to a loot table")