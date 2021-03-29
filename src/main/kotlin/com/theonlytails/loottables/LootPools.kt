package com.theonlytails.loottables

import net.minecraft.loot.LootEntry
import net.minecraft.loot.LootPool
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.loot.functions.ILootFunction

@LootTablesDsl
fun LootEntry.Builder<*>.add(pool: LootPool.Builder): LootPool.Builder = pool.add(this)

@LootTablesDsl
fun LootPool.Builder.add(vararg entries: LootEntry.Builder<*>) = entries.forEach { add(it) }

@LootTablesDsl
fun LootPool.Builder.condition(getCondition: () -> ILootCondition.IBuilder): LootPool.Builder = `when`(getCondition())

@LootTablesDsl
fun LootPool.Builder.function(getFunction: () -> ILootFunction.IBuilder): LootPool.Builder = apply(getFunction())