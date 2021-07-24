package com.theonlytails.loottables

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.IntRange
import net.minecraft.world.level.storage.loot.functions.*
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay.explosionDecay
import net.minecraft.world.level.storage.loot.functions.CopyBlockState.copyState
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction.copyName
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction.enchantWithLevels
import net.minecraft.world.level.storage.loot.functions.LimitCount.limitCount
import net.minecraft.world.level.storage.loot.functions.SetContainerContents.setContents
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction.setCount
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction.setDamage
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction.setTag
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction.stewEffect
import net.minecraft.world.level.storage.loot.providers.nbt.NbtProvider
import net.minecraft.world.level.storage.loot.providers.number.*

typealias BinomialRange = BinomialDistributionGenerator
typealias ConditionalFunctionBuilder = LootItemConditionalFunction.Builder<*>

/**
 * Adds a [LootConditionBuilder] to a [ConditionalFunctionBuilder].
 *
 * @author TheOnlyTails
 */
@LootTables
fun ConditionalFunctionBuilder.condition(getLootConditionBuilder: () -> LootConditionBuilder) =
	`when`(getLootConditionBuilder())
		?: throw LootTableCreationException("Something went wrong while adding a condition to a function")

/**
 * Creates a [SetItemCountFunction] loot function.
 *
 * @param value the range parameter of the function.
 * @author TheOnlyTails
 */
@LootTables
fun setCount(value: NumberProvider, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	setCount(value).body()

/**
 * Creates a [SetItemCountFunction] loot function with a [ConstantValue].
 *
 * @author TheOnlyTails
 */
@LootTables
fun setConstantCount(value: Float, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	setCount(constantValue(value)).body()

/**
 * Creates a [SetItemCountFunction] loot function with a [BinomialRange].
 *
 * @param amount the amount of trails in the range.
 * @param chance the chance of success in a trail.
 * @author TheOnlyTails
 */
@LootTables
fun setBinomialCount(
	amount: Int,
	chance: Float,
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }
) = setCount(binomialRange(amount, chance)).body()

/**
 * Creates a [SetItemCountFunction] loot function with a [UniformGenerator].
 *
 * @param min the lower bound of the range.
 * @param max the upper bound of the range.
 * @author TheOnlyTails
 */
@LootTables
fun setUniformCount(
	min: Float,
	max: Float,
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }
) = setCount(uniformGenerator(min, max)).body()

/**
 * Creates an [EnchantWithLevelsFunction] loot function.
 *
 * @param levels the range parameter of the function.
 * @author TheOnlyTails
 */
@LootTables
fun enchantWithLevels(
	levels: NumberProvider,
	body: EnchantWithLevelsFunction.Builder.() -> EnchantWithLevelsFunction.Builder = { this }
) = enchantWithLevels(levels).body()

/**
 * Creates an [EnchantRandomlyFunction] loot function.
 *
 * @author TheOnlyTails
 */
@LootTables
fun enchantRandomly(body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	EnchantRandomlyFunction.randomApplicableEnchantment().body()

/**
 * Creates a [SetNbtFunction] loot function.
 *
 * @param tag the [CompoundTag] parameter of the function.
 * @author TheOnlyTails
 */
@LootTables
fun setNbt(tag: CompoundTag, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) = setTag(tag).body()

/**
 * Creates a [SmeltItemFunction] loot function.
 *
 * @author TheOnlyTails
 */
@LootTables
fun furnaceSmelt(body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) = SmeltItemFunction.smelted().body()

/**
 * Creates an [LootingEnchantFunction] loot function.
 *
 * @param value the range parameter of the function.
 * @author TheOnlyTails
 */
@LootTables
fun looting(
	value: NumberProvider,
	body: LootingEnchantFunction.Builder.() -> LootingEnchantFunction.Builder = { this }
) =
	LootingEnchantFunction.lootingMultiplier(value).body()

/**
 * Creates an [SetItemDamageFunction] loot function.
 *
 * @param damage the range of damage of the function.
 * @author TheOnlyTails
 */
@LootTables
fun setDamage(damage: NumberProvider, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	setDamage(damage).body()

/**
 * Creates an [ExplorationMapFunction] loot function.
 *
 * @author TheOnlyTails
 */
@LootTables
fun explorationMap(body: ExplorationMapFunction.Builder.() -> ExplorationMapFunction.Builder = { this }) =
	ExplorationMapFunction.makeExplorationMap().body()

/**
 * Creates an [SetStewEffectFunction] loot function.
 *
 * @author TheOnlyTails
 */
@LootTables
fun stewEffect(body: SetStewEffectFunction.Builder.() -> SetStewEffectFunction.Builder = { this }) = stewEffect().body()

/**
 * Creates an [CopyNameFunction] loot function.
 *
 * @param source the type of [CopyNameFunction.NameSource] to copy the name from.
 * @author TheOnlyTails
 */
@LootTables
fun copyName(source: CopyNameFunction.NameSource, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	copyName(source).body()

/**
 * Creates an [SetContainerContents] loot function.
 *
 * @author TheOnlyTails
 */
@LootTables
fun setContents(body: SetContainerContents.Builder.() -> SetContainerContents.Builder = { this }) = setContents().body()

/**
 * Creates an [LimitCount] loot function.
 *
 * @param limiter the limiter of the function.
 * @author TheOnlyTails
 */
@LootTables
fun limitCount(limiter: IntRange, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	limitCount(limiter).body()

/**
 * Creates an [ApplyBonusCount] loot function of type [ApplyBonusCount.addUniformBonusCount].
 *
 * @param enchantment the enchantment of the function.
 * @param bonusMultiplier the bonus multiplier passed to the [ApplyBonusCount.UniformBonusCount] of the function
 * @author TheOnlyTails
 */
@LootTables
fun uniformBonusCount(
	enchantment: Enchantment,
	bonusMultiplier: Int = 1,
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this },
) = ApplyBonusCount.addUniformBonusCount(enchantment, bonusMultiplier).body()

/**
 * Creates an [ApplyBonusCount] loot function of type [ApplyBonusCount.addBonusBinomialDistributionCount].
 *
 * @param enchantment the enchantment of the function.
 * @param chance the chance parameter passed to the [ApplyBonusCount.BinomialWithBonusCount] of the function.
 * @param extraRounds the extra rounds parameter passed to the [ApplyBonusCount.BinomialWithBonusCount] of the function.
 * @author TheOnlyTails
 */
@LootTables
fun bonusBinomialDistributionCount(
	enchantment: Enchantment,
	chance: Float,
	extraRounds: Int,
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this },
) = ApplyBonusCount.addBonusBinomialDistributionCount(enchantment, chance, extraRounds).body()

/**
 * Creates an [ApplyBonusCount] loot function of type [ApplyBonusCount.addOreBonusCount] with the [ApplyBonusCount.OreDrops].
 *
 * @param enchantment the [Enchantment] of the function.
 * @author TheOnlyTails
 */
@LootTables
fun oreBonusCount(enchantment: Enchantment, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	ApplyBonusCount.addOreBonusCount(enchantment).body()

/**
 * Creates an [ApplyExplosionDecay] loot function.
 *
 * @author TheOnlyTails
 */
@LootTables
fun explosionDecay(body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) = explosionDecay().body()

/**
 * Creates an [CopyNbtFunction] loot function.
 *
 * @param source the type of [NbtProvider] to copy the [CompoundTag] from.
 * @author TheOnlyTails
 */
@LootTables
fun copyNbt(source: NbtProvider, body: CopyNbtFunction.Builder.() -> CopyNbtFunction.Builder = { this }) =
	CopyNbtFunction.copyData(source).body()

/**
 * Creates an [CopyBlockState] loot function.
 *
 * @param block the [Block] to copy the [BlockState] from.
 * @author TheOnlyTails
 */
@LootTables
fun copyState(block: Block, body: CopyBlockState.Builder.() -> CopyBlockState.Builder = { this }) =
	copyState(block).body()