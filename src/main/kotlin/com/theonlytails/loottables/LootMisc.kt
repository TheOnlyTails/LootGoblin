package com.theonlytails.loottables

import net.minecraft.world.level.storage.loot.providers.number.*
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator.binomial
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly
import kotlin.ranges.IntRange
import net.minecraft.world.level.storage.loot.IntRange as LootIntRange

/**
 * Creates an [LootIntRange] with an exact value.
 *
 * @author TheOnlyTails
 */
@LootTables
fun intValue(value: Int) = LootIntRange.exact(value)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates an [LootIntRange] with lower and upper bounds.
 *
 * @author TheOnlyTails
 */
@LootTables
fun intRange(min: Int, max: Int) = LootIntRange.range(min, max)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates an [LootIntRange] with a lower bound.
 *
 * @author TheOnlyTails
 */
@LootTables
fun intClamperLower(min: Int) = LootIntRange.lowerBound(min)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates an [LootIntRange] with an upper bound.
 *
 * @author TheOnlyTails
 */
@LootTables
fun intClamperUpper(max: Int) = LootIntRange.upperBound(max)
	?: throw LootTableCreationException("Something went wrong while creating an IntLimiter")

/**
 * Creates a [UniformGenerator] with lower and upper bounds.
 *
 * @author TheOnlyTails
 */
@LootTables
fun uniformGenerator(min: Float, max: Float): UniformGenerator = UniformGenerator.between(min, max)

/**
 * Creates a [UniformGenerator] ([NumberProvider]) with a Kotlin [IntRange].
 *
 * @author TheOnlyTails
 */
@LootTables
fun uniformGenerator(range: IntRange): UniformGenerator =
	UniformGenerator.between(range.first.toFloat(), range.last.toFloat())

/**
 * Creates a [ConstantValue].
 *
 * @author TheOnlyTails
 */
@LootTables
fun constantValue(value: Float): ConstantValue = exactly(value)

/**
 * Creates an [BinomialDistributionGenerator] which generates a number following a binomial distribution.
 *
 * @param amount the amount of trials in the range.
 * @param chance the chance of success in a trial.
 * @author TheOnlyTails
 */
@LootTables
fun binomialRange(amount: Int, chance: Float): BinomialDistributionGenerator = binomial(amount, chance)