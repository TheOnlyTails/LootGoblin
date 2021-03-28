package com.theonlytails.loottables

import net.minecraft.loot.*

@LootTablesDsl
fun intClamper(min: Int, max: Int): IntClamper = IntClamper.clamp(min, max)

@LootTablesDsl
fun intClamperLower(min: Int): IntClamper = IntClamper.clamp(min, Int.MAX_VALUE)

@LootTablesDsl
fun intClamperUpper(max: Int): IntClamper = IntClamper.clamp(Int.MIN_VALUE, max)

@LootTablesDsl
fun randomRangeValue(min: Float, max: Float) = RandomValueRange(min, max)

@LootTablesDsl
fun constantRange(value: Int) = ConstantRange(value)

@LootTablesDsl
fun binomialRange(n: Int, p: Float) = BinomialRange(n, p)