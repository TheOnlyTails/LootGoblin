package com.theonlytails.loottables

import net.minecraft.advancements.criterion.*
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.loot.LootContext.EntityTarget
import net.minecraft.loot.conditions.*
import net.minecraft.util.math.BlockPos

@LootTablesDsl
fun inverted(condition: ILootCondition.IBuilder): ILootCondition.IBuilder = Inverted.invert(condition)

@LootTablesDsl
fun inverted(condition: ILootCondition.IBuilder, body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder) =
	inverted(condition).body()

@LootTablesDsl
fun alternative(conditions: Collection<ILootCondition.IBuilder>): Alternative.Builder =
	Alternative.alternative(*conditions.toTypedArray())

@LootTablesDsl
fun alternative(
	conditions: Collection<ILootCondition.IBuilder>,
	body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder,
) = alternative(conditions).body()

@LootTablesDsl
fun randomChance(chance: Float): ILootCondition.IBuilder = RandomChance.randomChance(chance)

@LootTablesDsl
fun randomChance(chance: Float, body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder) =
	randomChance(chance).body()

@LootTablesDsl
fun randomChanceWithLooting(chance: Float, lootingMultiplier: Float): ILootCondition.IBuilder =
	RandomChanceWithLooting.randomChanceAndLootingBoost(chance, lootingMultiplier)

@LootTablesDsl
fun inverted(
	chance: Float,
	lootingMultiplier: Float,
	body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder,
) = randomChanceWithLooting(chance, lootingMultiplier).body()

@LootTablesDsl
fun entityProperties(target: EntityTarget, predicate: EntityPredicate.Builder): ILootCondition.IBuilder =
	EntityHasProperty.hasProperties(target, predicate)

@LootTablesDsl
fun entityProperties(
	target: EntityTarget,
	predicate: EntityPredicate.Builder,
	body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder,
) = entityProperties(target, predicate).body()

@LootTablesDsl
fun entityPresent(target: EntityTarget): ILootCondition.IBuilder =
	EntityHasProperty.entityPresent(target)

@LootTablesDsl
fun entityPresent(target: EntityTarget, body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder) =
	entityPresent(target).body()

@LootTablesDsl
fun killedByPlayer(): ILootCondition.IBuilder = KilledByPlayer.killedByPlayer()

@LootTablesDsl
fun killedByPlayer(body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder) = killedByPlayer().body()

@LootTablesDsl
fun blockStateProperty(block: Block): BlockStateProperty.Builder = BlockStateProperty.hasBlockStateProperties(block)

@LootTablesDsl
fun blockStateProperty(block: Block, body: BlockStateProperty.Builder.() -> BlockStateProperty.Builder) =
	blockStateProperty(block).body()

@LootTablesDsl
fun matchTool(predicate: ItemPredicate.Builder): ILootCondition.IBuilder = MatchTool.toolMatches(predicate)

@LootTablesDsl
fun matchTool(predicate: ItemPredicate.Builder, body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder) =
	matchTool(predicate).body()

@LootTablesDsl
fun tableBonus(enchantment: Enchantment, vararg values: Float): ILootCondition.IBuilder =
	TableBonus.bonusLevelFlatChance(enchantment, *values)

@LootTablesDsl
fun tableBonus(
	enchantment: Enchantment,
	vararg values: Float,
	body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder,
) = tableBonus(enchantment, *values).body()

@LootTablesDsl
fun survivesExplosion(): ILootCondition.IBuilder = SurvivesExplosion.survivesExplosion()

@LootTablesDsl
fun survivesExplosion(body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder) = survivesExplosion().body()

@LootTablesDsl
fun damageSourceProperties(predicate: DamageSourcePredicate.Builder): ILootCondition.IBuilder =
	DamageSourceProperties.hasDamageSource(predicate)

@LootTablesDsl
fun damageSourceProperties(
	predicate: DamageSourcePredicate.Builder,
	body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder,
) = damageSourceProperties(predicate).body()

@LootTablesDsl
fun locationCheck(pos: BlockPos = BlockPos.ZERO, predicate: LocationPredicate.Builder): ILootCondition.IBuilder =
	LocationCheck.checkLocation(predicate, pos)

@LootTablesDsl
fun locationCheck(
	pos: BlockPos,
	predicate: LocationPredicate.Builder,
	body: ILootCondition.IBuilder.() -> ILootCondition.IBuilder,
) = locationCheck(pos, predicate).body()