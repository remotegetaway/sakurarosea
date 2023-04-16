package com.remotegetaway.sakurarosea.init;

import com.remotegetaway.sakurarosea.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.remotegetaway.sakurarosea.feature.tree.trunkplacers.CanopyTree4BranchTrunkPlacer;
import com.remotegetaway.sakurarosea.feature.tree.trunkplacers.FallenStraightTrunkPlacer;
import com.remotegetaway.sakurarosea.feature.tree.trunkplacers.MegaTrunkPlacer;
import com.remotegetaway.sakurarosea.init.helpers.WoodBlocks;
import com.remotegetaway.sakurarosea.SakuraRosea;
import com.remotegetaway.sakurarosea.feature.tree.foliageplacers.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class SakuraRoseaConfiguredFeatures {
	public static RegistryKey<ConfiguredFeature<?, ?>> SAKURA_SHRUB = createRegistryKey("sakura_shrub");
	public static RegistryKey<ConfiguredFeature<?, ?>> SAKURA_TREE = createRegistryKey("sakura_tree");
	public static RegistryKey<ConfiguredFeature<?, ?>> DARK_SAKURA_TREE = createRegistryKey("dark_sakura_tree");


	public static void populate(FabricDynamicRegistryProvider.Entries entries) {

		entries.add(SAKURA_SHRUB, configureFeature(Feature.TREE, shrubOf(SakuraRoseaBlocks.SAKURA.log.getDefaultState(), SakuraRoseaBlocks.SAKURA_SHRUB_LEAVES.getDefaultState(), SakuraRoseaBlocks.SAKURA_SHRUB_SAPLING.getDefaultState())));


		entries.add(SAKURA_TREE, configureFeature(Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(SakuraRoseaBlocks.SAKURA.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(SakuraRoseaBlocks.SAKURA.leaves),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.build()));

		entries.add(DARK_SAKURA_TREE, configureFeature(Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(SakuraRoseaBlocks.SAKURA.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(SakuraRoseaBlocks.DARK_SAKURA_LEAVES),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.build()));

	}

	static TreeFeatureConfig canopyOf(WoodBlocks woodBlocks, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return canopyOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling, trunkPlacer, decorators);
	}

	static TreeFeatureConfig canopyOf(BlockState log, BlockState leaves, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				trunkPlacer,
				SimpleBlockStateProvider.of(leaves),
				new CanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(3, 0 , 1))
				.decorators(decorators)
				.build();
	}


	static TreeFeatureConfig shrubOf(WoodBlocks woodBlocks, BlockState sapling) {
		return shrubOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling);
	}

	static TreeFeatureConfig shrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 0, 0),
				SimpleBlockStateProvider.of(leaves),
				new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig dotShrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 1, 0),
				SimpleBlockStateProvider.of(leaves),
				new DotShrubPlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}


	public static RegistryKey<ConfiguredFeature<?, ?>> createRegistryKey(String name) {
		return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(SakuraRosea.MOD_ID, name));
	}

	public static <FC extends FeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, ?> configureFeature(F feature, FC config) {
		return new ConfiguredFeature<>(feature, config);
	}

	public static void init() {
		// This just creates the registry keys.  Configured Features are requested and consumed by datagen now.
	}
}
