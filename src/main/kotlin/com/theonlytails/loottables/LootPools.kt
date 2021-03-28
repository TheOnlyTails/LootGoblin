package com.theonlytails.loottables

import net.minecraft.loot.LootPool
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.loot.functions.ILootFunction

@LootTablesDsl
fun LootPool.Builder.condition(getCondition: () -> ILootCondition.IBuilder): LootPool.Builder = `when`(getCondition())

@LootTablesDsl
fun LootPool.Builder.function(getFunction: () -> ILootFunction.IBuilder): LootPool.Builder = apply(getFunction())