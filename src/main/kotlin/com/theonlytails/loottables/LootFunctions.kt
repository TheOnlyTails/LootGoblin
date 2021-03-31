package com.theonlytails.loottables

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.enchantment.Enchantment
import net.minecraft.loot.*
import net.minecraft.loot.functions.*
import net.minecraft.loot.functions.CopyBlockState.copyState
import net.minecraft.loot.functions.CopyName.copyName
import net.minecraft.loot.functions.EnchantWithLevels.enchantWithLevels
import net.minecraft.loot.functions.ExplosionDecay.explosionDecay
import net.minecraft.loot.functions.LimitCount.limitCount
import net.minecraft.loot.functions.SetContents.setContents
import net.minecraft.loot.functions.SetCount.setCount
import net.minecraft.loot.functions.SetDamage.setDamage
import net.minecraft.loot.functions.SetStewEffect.stewEffect
import net.minecraft.nbt.CompoundNBT
import net.minecraft.loot.LootFunction.Builder as Function
import net.minecraft.loot.conditions.ILootCondition.IBuilder as Condition

/**
 * Adds a [Condition] to a [Function].
 *
 * @receiver a loot function.
 * @param getCondition a lambda that returns the condition.
 * @return the original function, with the condition added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Function<*>.condition(getCondition: () -> Condition): Function<*> =
	`when`(getCondition())

/**
 * Creates a [SetCount] loot function.
 *
 * @param value the range parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setCount(value: IRandomRange, body: Function<*>.() -> Function<*> = { this }) =
	setCount(value).body()

/**
 * Creates a [SetCount] loot function with a [ConstantRange].
 *
 * @param value the value of the constant range.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setConstantCount(value: Int, body: Function<*>.() -> Function<*> = { this }) = setCount(constantRange(value)).body()

/**
 * Creates a [SetCount] loot function with a [BinomialRange].
 *
 * @param amount the amount of trails in the range.
 * @param chance the chance of success in a trail.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setBinomialCount(
	amount: Int,
	chance: Float,
	body: LootFunction.Builder<*>.() -> LootFunction.Builder<*> = { this }
) = setCount(binomialRange(amount, chance)).body()

/**
 * Creates a [SetCount] loot function with a [RandomValueRange].
 *
 * @param min the lower bound of the range.
 * @param max the upper bound of the range.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setRandomCount(
	min: Float,
	max: Float,
	body: LootFunction.Builder<*>.() -> LootFunction.Builder<*> = { this }
) = setCount(randomRangeValue(min, max)).body()

/**
 * Creates an [EnchantWithLevels] loot function.
 *
 * @param levels the range parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantWithLevels(
	levels: IRandomRange,
	body: EnchantWithLevels.Builder.() -> EnchantWithLevels.Builder = { this }
) = enchantWithLevels(levels).body()

/**
 * Creates an [EnchantRandomly] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantRandomly(body: Function<*>.() -> Function<*> = { this }) =
	EnchantRandomly.randomApplicableEnchantment().body()

/**
 * Creates an [SetNBT] loot function.
 *
 * @param tag the [CompoundNBT] parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setNbt(tag: CompoundNBT, body: Function<*>.() -> Function<*> = { this }) = SetNBT.setTag(tag).body()

/**
 * Creates an [Smelt] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun furnaceSmelt(body: Function<*>.() -> Function<*> = { this }) = Smelt.smelted().body()

/**
 * Creates an [LootingEnchantBonus] loot function.
 *
 * @param value the range parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun looting(value: RandomValueRange, body: LootingEnchantBonus.Builder.() -> LootingEnchantBonus.Builder = { this }) =
	LootingEnchantBonus.lootingMultiplier(value).body()

/**
 * Creates an [SetDamage] loot function.
 *
 * @param damage the range of damage of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setDamage(damage: RandomValueRange, body: Function<*>.() -> Function<*> = { this }) =
	setDamage(damage).body()

/**
 * Creates an [ExplorationMap] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun explorationMap(body: ExplorationMap.Builder.() -> ExplorationMap.Builder = { this }) =
	ExplorationMap.makeExplorationMap().body()

/**
 * Creates an [SetStewEffect] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun stewEffect(body: SetStewEffect.Builder.() -> SetStewEffect.Builder = { this }) = stewEffect().body()

/**
 * Creates an [CopyName] loot function.
 *
 * @param source the type of [CopyName.Source] to copy the name from.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyName(source: CopyName.Source, body: Function<*>.() -> Function<*> = { this }) =
	copyName(source).body()

/**
 * Creates an [SetContents] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setContents(body: SetContents.Builder.() -> SetContents.Builder = { this }) = setContents().body()

/**
 * Creates an [LimitCount] loot function.
 *
 * @param limiter the limiter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun limitCount(limiter: IntClamper, body: Function<*>.() -> Function<*> = { this }) = limitCount(limiter).body()

/**
 * Creates an [ApplyBonus] loot function of type [ApplyBonus.addUniformBonusCount].
 *
 * @param enchantment the enchantment of the function.
 * @param bonusMultiplier the bonus multiplier passed to the [ApplyBonus.UniformBonusCountFormula] of the function
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun uniformBonusCount(
	enchantment: Enchantment,
	bonusMultiplier: Int = 1,
	body: Function<*>.() -> Function<*> = { this },
) = ApplyBonus.addUniformBonusCount(enchantment, bonusMultiplier).body()

/**
 * Creates an [ApplyBonus] loot function of type [ApplyBonus.addBonusBinomialDistributionCount].
 *
 * @param enchantment the enchantment of the function.
 * @param chance the chance parameter passed to the [ApplyBonus.BinomialWithBonusCountFormula] of the function.
 * @param extraRounds the extra rounds parameter passed to the [ApplyBonus.BinomialWithBonusCountFormula] of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun bonusBinomialDistributionCount(
	enchantment: Enchantment,
	chance: Float,
	extraRounds: Int,
	body: Function<*>.() -> Function<*> = { this },
) = ApplyBonus.addBonusBinomialDistributionCount(enchantment, chance, extraRounds).body()

/**
 * Creates an [ApplyBonus] loot function of type [ApplyBonus.addOreBonusCount] with the [ApplyBonus.OreDropsFormula].
 *
 * @param enchantment the [Enchantment] of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun oreBonusCount(enchantment: Enchantment, body: Function<*>.() -> Function<*> = { this }) =
	ApplyBonus.addOreBonusCount(enchantment).body()

/**
 * Creates an [ExplosionDecay] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun explosionDecay(body: Function<*>.() -> Function<*> = { this }) = explosionDecay().body()

/**
 * Creates an [CopyNbt] loot function.
 *
 * @param source the type of [CopyNbt.Source] to copy the [CompoundNBT] from.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyNbt(source: CopyNbt.Source, body: CopyNbt.Builder.() -> CopyNbt.Builder = { this }) =
	CopyNbt.copyData(source).body()

/**
 * Creates an [CopyBlockState] loot function.
 *
 * @param block the [Block] to copy the [BlockState] from.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyState(block: Block, body: CopyBlockState.Builder.() -> CopyBlockState.Builder = { this }) =
	copyState(block).body()