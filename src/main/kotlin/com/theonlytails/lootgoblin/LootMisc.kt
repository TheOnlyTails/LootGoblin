@file:JvmName("LootMisc")
package com.theonlytails.lootgoblin

import net.minecraft.world.level.storage.loot.providers.number.*
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator.binomial
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly
import kotlin.ranges.IntRange
import net.minecraft.world.level.storage.loot.IntRange as LootIntRange

/**
 * Creates an [LootIntRange] with an exact value.
 */
@LootGoblinDsl
fun intValue(value: Int) = LootIntRange.exact(value)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates an [LootIntRange] with lower and upper bounds.
 */
@LootGoblinDsl
fun intRange(min: Int, max: Int) = LootIntRange.range(min, max)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates an [LootIntRange] with a lower bound.
 */
@LootGoblinDsl
fun intClamperLower(min: Int) = LootIntRange.lowerBound(min)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates an [LootIntRange] with an upper bound.

 */
@LootGoblinDsl
fun intClamperUpper(max: Int) = LootIntRange.upperBound(max)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates a [UniformGenerator] with lower and upper bounds.

 */
@LootGoblinDsl
fun uniformGenerator(min: Float, max: Float): UniformGenerator = UniformGenerator.between(min, max)

/**
 * Creates a [UniformGenerator] ([NumberProvider]) with a Kotlin [IntRange].

 */
@LootGoblinDsl
fun uniformGenerator(range: IntRange): UniformGenerator =
	UniformGenerator.between(range.first.toFloat(), range.last.toFloat())

/**
 * Creates a [ConstantValue].

 */
@LootGoblinDsl
fun constantValue(value: Float): ConstantValue = exactly(value)

/**
 * Creates an [BinomialDistributionGenerator] which generates a number following a binomial distribution.
 *
 * @param amount the amount of trials in the range.
 * @param chance the chance of success in a trial.

 */
@LootGoblinDsl
fun binomialRange(amount: Int, chance: Float): BinomialDistributionGenerator = binomial(amount, chance)