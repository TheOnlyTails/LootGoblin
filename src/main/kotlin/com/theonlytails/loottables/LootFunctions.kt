package com.theonlytails.loottables

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.loot.*
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.loot.functions.*
import net.minecraft.nbt.CompoundNBT

@LootTablesDsl
fun LootFunction.Builder<*>.condition(getCondition: () -> ILootCondition.IBuilder): LootFunction.Builder<*> =
	`when`(getCondition())

@LootTablesDsl
fun setCount(value: IRandomRange): LootFunction.Builder<*> = SetCount.setCount(value)

@LootTablesDsl
fun setCount(value: IRandomRange, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = setCount(value).body()

@LootTablesDsl
fun enchantWithLevels(levels: IRandomRange): EnchantWithLevels.Builder = EnchantWithLevels.enchantWithLevels(levels)

@LootTablesDsl
fun enchantWithLevels(levels: IRandomRange, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) =
	enchantWithLevels(levels).body()

@LootTablesDsl
fun enchantRandomly(): LootFunction.Builder<*> = EnchantRandomly.randomApplicableEnchantment()

@LootTablesDsl
fun enchantRandomly(body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = enchantRandomly().body()

@LootTablesDsl
fun setNbt(tag: CompoundNBT): LootFunction.Builder<*> = SetNBT.setTag(tag)

@LootTablesDsl
fun setNbt(tag: CompoundNBT, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = setNbt(tag).body()

@LootTablesDsl
fun furnaceSmelt(): LootFunction.Builder<*> = Smelt.smelted()

@LootTablesDsl
fun furnaceSmelt(body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = furnaceSmelt().body()

@LootTablesDsl
fun looting(value: RandomValueRange): LootingEnchantBonus.Builder = LootingEnchantBonus.lootingMultiplier(value)

@LootTablesDsl
fun looting(value: RandomValueRange, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) =
	looting(value).body()

@LootTablesDsl
fun setDamage(damage: RandomValueRange): LootFunction.Builder<*> = SetDamage.setDamage(damage)

@LootTablesDsl
fun setDamage(damage: RandomValueRange, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) =
	setDamage(damage).body()

@LootTablesDsl
fun explorationMap(): ExplorationMap.Builder = ExplorationMap.makeExplorationMap()

@LootTablesDsl
fun explorationMap(body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = explorationMap().body()

@LootTablesDsl
fun stewEffect(): SetStewEffect.Builder = SetStewEffect.stewEffect()

@LootTablesDsl
fun stewEffect(body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = stewEffect().body()

@LootTablesDsl
fun copyName(source: CopyName.Source): LootFunction.Builder<*> = CopyName.copyName(source)

@LootTablesDsl
fun copyName(source: CopyName.Source, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) =
	copyName(source).body()

@LootTablesDsl
fun setContents(): SetContents.Builder = SetContents.setContents()

@LootTablesDsl
fun setContents(body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = setContents().body()

@LootTablesDsl
fun limitCount(limiter: IntClamper): LootFunction.Builder<*> = LimitCount.limitCount(limiter)

@LootTablesDsl
fun limitCount(limiter: IntClamper, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) =
	limitCount(limiter).body()

@LootTablesDsl
fun uniformBonusCount(enchantment: Enchantment, bonusMultiplier: Int = 1): LootFunction.Builder<*> =
	ApplyBonus.addUniformBonusCount(enchantment, bonusMultiplier)

@LootTablesDsl
fun uniformBonusCount(
	enchantment: Enchantment,
	bonusMultiplier: Int = 1,
	body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>,
) = uniformBonusCount(enchantment, bonusMultiplier).body()

@LootTablesDsl
fun bonusBinomialDistributionCount(enchantment: Enchantment, chance: Float, extraRounds: Int): LootFunction.Builder<*> =
	ApplyBonus.addBonusBinomialDistributionCount(enchantment, chance, extraRounds)

@LootTablesDsl
fun bonusBinomialDistributionCount(
	enchantment: Enchantment,
	chance: Float,
	extraRounds: Int,
	body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>,
) = bonusBinomialDistributionCount(enchantment, chance, extraRounds).body()

@LootTablesDsl
fun oreBonusCount(enchantment: Enchantment): LootFunction.Builder<*> = ApplyBonus.addOreBonusCount(enchantment)

@LootTablesDsl
fun oreBonusCount(enchantment: Enchantment, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) =
	oreBonusCount(enchantment).body()

@LootTablesDsl
fun explosionDecay(): LootFunction.Builder<*> = ExplosionDecay.explosionDecay()

@LootTablesDsl
fun explosionDecay(body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = explosionDecay().body()

@LootTablesDsl
fun copyNbt(source: CopyNbt.Source): CopyNbt.Builder = CopyNbt.copyData(source)

@LootTablesDsl
fun copyNbt(source: CopyNbt.Source, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = copyNbt(source).body()

@LootTablesDsl
fun copyState(block: Block): CopyBlockState.Builder = CopyBlockState.copyState(block)

@LootTablesDsl
fun copyState(block: Block, body: LootFunction.Builder<*>.() -> LootFunction.Builder<*>) = copyState(block).body()