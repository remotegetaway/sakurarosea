package com.remotegetaway.sakurarosea.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SakuraRoseaSaplingGenerator extends SaplingGenerator {
	public final Supplier<RegistryKey<ConfiguredFeature<?, ?>>> tree;

	public SakuraRoseaSaplingGenerator(Supplier<RegistryKey<ConfiguredFeature<?, ?>>> tree) {
		this.tree = tree;
	}

	@Nullable
	@Override
	protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return this.tree.get();
	}
}
