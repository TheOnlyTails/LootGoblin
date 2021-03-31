package com.theonlytails.loottables

import net.minecraft.loot.*

/**
 * Creates an [IntClamper] with lower and upper bounds.
 *
 * @param min the lower bound of the clamper.
 * @param max the upper bound of the clamper.
 * @return the clamper.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun intClamper(min: Int, max: Int): IntClamper = IntClamper.clamp(min, max)

/**
 * Creates an [IntClamper] with a lower bound.
 *
 * @param min the lower bound of the clamper.
 * @return the clamper.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun intClamperLower(min: Int): IntClamper = IntClamper.clamp(min, Int.MAX_VALUE)

/**
 * Creates an [IntClamper] with an upper bound.
 *
 * @param max the upper bound of the clamper.
 * @return the clamper.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun intClamperUpper(max: Int): IntClamper = IntClamper.clamp(Int.MIN_VALUE, max)

/**
 * Creates a [RandomValueRange] with lower and upper bounds.
 *
 * @param min the lower bound of the range.
 * @param max the upper bound of the range.
 * @return the random range.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun randomRangeValue(min: Float, max: Float) = RandomValueRange(min, max)

/**
 * Creates a [RandomValueRange] with a Kotlin [IntRange].
 *
 * @param range a Kotlin range.
 * @return the random range.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun randomRangeValue(range: IntRange) = randomRangeValue(range.first.toFloat(), range.last.toFloat())

/**
 * Creates a [ConstantRange].
 *
 * @param value the value of the range.
 * @return the constant range.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun constantRange(value: Int) = ConstantRange(value)

/**
 * Creates an [BinomialRange] with its amount and chance..
 *
 * @param amount the amount of trails in the range.
 * @param chance the chance of success in a trail.
 * @return the binomial range.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun binomialRange(amount: Int, chance: Float) = BinomialRange(amount, chance)