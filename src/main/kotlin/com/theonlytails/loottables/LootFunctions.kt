package com.theonlytails.loottables

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.enchantment.Enchantment
import net.minecraft.loot.*
import net.minecraft.loot.functions.*
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
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setCount(value: IRandomRange): Function<*> = SetCount.setCount(value)

/**
 * Creates a [SetCount] loot function.
 *
 * @param value the range parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */

@LootTablesDsl
fun setCount(value: IRandomRange, body: Function<*>.() -> Function<*>) = setCount(value).body()

/**
 * Creates a [SetCount] loot function with a [ConstantRange].
 *
 * @param value the value of the constant range.
 * @return the [Function].
 * @author TheOnlyTails
 */

@LootTablesDsl
fun setConstantCount(value: Int): Function<*> = SetCount.setCount(constantRange(value))

/**
 * Creates a [SetCount] loot function with a [ConstantRange].
 *
 * @param value the value of the constant range.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */

@LootTablesDsl
fun setConstantCount(value: Int, body: Function<*>.() -> Function<*>) = setCount(constantRange(value)).body()

/**
 * Creates an [EnchantWithLevels] loot function.
 *
 * @param levels the range parameter of the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantWithLevels(levels: IRandomRange): EnchantWithLevels.Builder = EnchantWithLevels.enchantWithLevels(levels)

/**
 * Creates an [EnchantWithLevels] loot function.
 *
 * @param levels the range parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantWithLevels(levels: IRandomRange, body: Function<*>.() -> Function<*>) =
	enchantWithLevels(levels).body()

/**
 * Creates an [EnchantRandomly] loot function.
 *
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantRandomly(): Function<*> = EnchantRandomly.randomApplicableEnchantment()

/**
 * Creates an [EnchantRandomly] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantRandomly(body: Function<*>.() -> Function<*>) = enchantRandomly().body()

/**
 * Creates a [SetNBT] loot function.
 *
 * @param tag the [CompoundNBT] parameter of the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setNbt(tag: CompoundNBT): Function<*> = SetNBT.setTag(tag)

/**
 * Creates an [SetNBT] loot function.
 *
 * @param tag the [CompoundNBT] parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setNbt(tag: CompoundNBT, body: Function<*>.() -> Function<*>) = setNbt(tag).body()

/**
 * Creates an [Smelt] loot function.
 *
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun furnaceSmelt(): Function<*> = Smelt.smelted()

/**
 * Creates an [Smelt] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun furnaceSmelt(body: Function<*>.() -> Function<*>) = furnaceSmelt().body()

/**
 * Creates an [LootingEnchantBonus] loot function.
 *
 * @param value the range parameter of the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun looting(value: RandomValueRange): LootingEnchantBonus.Builder = LootingEnchantBonus.lootingMultiplier(value)

/**
 * Creates an [LootingEnchantBonus] loot function.
 *
 * @param value the range parameter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun looting(value: RandomValueRange, body: Function<*>.() -> Function<*>) =
	looting(value).body()

/**
 * Creates an [SetDamage] loot function.
 *
 * @param damage the range of damage of the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setDamage(damage: RandomValueRange): Function<*> = SetDamage.setDamage(damage)

/**
 * Creates an [SetDamage] loot function.
 *
 * @param damage the range of damage of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setDamage(damage: RandomValueRange, body: Function<*>.() -> Function<*>) =
	setDamage(damage).body()

/**
 * Creates an [ExplorationMap] loot function.
 *
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun explorationMap(): ExplorationMap.Builder = ExplorationMap.makeExplorationMap()

/**
 * Creates an [ExplorationMap] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun explorationMap(body: Function<*>.() -> Function<*>) = explorationMap().body()

/**
 * Creates an [SetStewEffect] loot function.
 *
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun stewEffect(): SetStewEffect.Builder = SetStewEffect.stewEffect()

/**
 * Creates an [SetStewEffect] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun stewEffect(body: Function<*>.() -> Function<*>) = stewEffect().body()

/**
 * Creates an [CopyName] loot function.
 *
 * @param source the type of [CopyName.Source] to copy the name from.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyName(source: CopyName.Source): Function<*> = CopyName.copyName(source)

/**
 * Creates an [CopyName] loot function.
 *
 * @param source the type of [CopyName.Source] to copy the name from.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyName(source: CopyName.Source, body: Function<*>.() -> Function<*>) =
	copyName(source).body()

/**
 * Creates an [SetContents] loot function.
 *
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setContents(): SetContents.Builder = SetContents.setContents()

/**
 * Creates an [SetContents] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun setContents(body: Function<*>.() -> Function<*>) = setContents().body()

/**
 * Creates an [LimitCount] loot function.
 *
 * @param limiter the limiter of the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun limitCount(limiter: IntClamper): Function<*> = LimitCount.limitCount(limiter)

/**
 * Creates an [LimitCount] loot function.
 *
 * @param limiter the limiter of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun limitCount(limiter: IntClamper, body: Function<*>.() -> Function<*>) =
	limitCount(limiter).body()

/**
 * Creates an [ApplyBonus] loot function of type [ApplyBonus.addUniformBonusCount].
 *
 * @param enchantment the enchantment of the function.
 * @param bonusMultiplier the bonus multiplier passed to the [ApplyBonus.UniformBonusCountFormula] of the function
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun uniformBonusCount(enchantment: Enchantment, bonusMultiplier: Int = 1): Function<*> =
	ApplyBonus.addUniformBonusCount(enchantment, bonusMultiplier)

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
	body: Function<*>.() -> Function<*>,
) = uniformBonusCount(enchantment, bonusMultiplier).body()

/**
 * Creates an [ApplyBonus] loot function of type [ApplyBonus.addBonusBinomialDistributionCount].
 *
 * @param enchantment the enchantment of the function.
 * @param chance the chance parameter passed to the formula of the function.
 * @param extraRounds the extra rounds parameter passed to the formula of the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun bonusBinomialDistributionCount(enchantment: Enchantment, chance: Float, extraRounds: Int): Function<*> =
	ApplyBonus.addBonusBinomialDistributionCount(enchantment, chance, extraRounds)

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
	body: Function<*>.() -> Function<*>,
) = bonusBinomialDistributionCount(enchantment, chance, extraRounds).body()

/**
 * Creates an [ApplyBonus] loot function of type [ApplyBonus.addOreBonusCount] with the [ApplyBonus.OreDropsFormula].
 *
 * @param enchantment the [Enchantment] of the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun oreBonusCount(enchantment: Enchantment): Function<*> = ApplyBonus.addOreBonusCount(enchantment)

/**
 * Creates an [ApplyBonus] loot function of type [ApplyBonus.addOreBonusCount] with the [ApplyBonus.OreDropsFormula].
 *
 * @param enchantment the [Enchantment] of the function.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun oreBonusCount(enchantment: Enchantment, body: Function<*>.() -> Function<*>) =
	oreBonusCount(enchantment).body()

/**
 * Creates an [ExplosionDecay] loot function.
 *
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun explosionDecay(): Function<*> = ExplosionDecay.explosionDecay()

/**
 * Creates an [ExplosionDecay] loot function.
 *
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun explosionDecay(body: Function<*>.() -> Function<*>) = explosionDecay().body()

/**
 * Creates an [CopyNbt] loot function.
 *
 * @param source the type of [CopyNbt.Source] to copy the [CompoundNBT] from.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyNbt(source: CopyNbt.Source): CopyNbt.Builder = CopyNbt.copyData(source)

/**
 * Creates an [CopyNbt] loot function.
 *
 * @param source the type of [CopyNbt.Source] to copy the [CompoundNBT] from.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyNbt(source: CopyNbt.Source, body: Function<*>.() -> Function<*>) = copyNbt(source).body()

/**
 * Creates an [CopyBlockState] loot function.
 *
 * @param block the [Block] to copy the [BlockState] from.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyState(block: Block): CopyBlockState.Builder = CopyBlockState.copyState(block)

/**
 * Creates an [CopyBlockState] loot function.
 *
 * @param block the [Block] to copy the [BlockState] from.
 * @param body a block of code that runs on the function.
 * @return the [Function].
 * @author TheOnlyTails
 */
@LootTablesDsl
fun copyState(block: Block, body: Function<*>.() -> Function<*>) = copyState(block).body()