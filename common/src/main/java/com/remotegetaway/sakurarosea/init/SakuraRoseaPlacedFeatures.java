package com.remotegetaway.sakurarosea.init;

import com.remotegetaway.sakurarosea.SakuraRosea;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class SakuraRoseaPlacedFeatures {

	private static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_VOLCANIC_ISLAND_GRASS_CONFIGURED = SakuraRoseaConfiguredFeatures.createRegistryKey("patch_volcanic_island_grass");

	private static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_DEAD_GRASS_CONFIGURED = SakuraRoseaConfiguredFeatures.createRegistryKey("patch_dead_grass");

	private static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_OUTBACK_BUSHLAND_GRASS_CONFIGURED = SakuraRoseaConfiguredFeatures.createRegistryKey("patch_outback_bushland_grass");

	private static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_OASIS_VEGETATION_CONFIGURED = SakuraRoseaConfiguredFeatures.createRegistryKey("patch_oasis_vegetation");

	private static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_LUSH_DESERT_VEGETATION_CONFIGURED = SakuraRoseaConfiguredFeatures.createRegistryKey("patch_lush_desert_vegetation");

	public static final RegistryKey<PlacedFeature> DENSE_SAKURA_TREES = createRegistryKey("dense_sakura_trees");
	public static final RegistryKey<PlacedFeature> DENSE_DARK_SAKURA_TREES = createRegistryKey("dense_dark_sakura_trees");
	public static final RegistryKey<PlacedFeature> DENSE_SAKURA_SHRUBS = createRegistryKey("dense_sakura_shrubs");

	private static final RegistryKey<ConfiguredFeature<?, ?>> OUTBACK_BUSHLAND_TREES_CONFIGURED = SakuraRoseaConfiguredFeatures.createRegistryKey("outback_bushland_trees");


	public static void populate(FabricDynamicRegistryProvider.Entries entries) {
		final BlockPredicate ON_DIRT = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT);
		final BlockPredicate ON_SAND = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.SAND);
		final BlockPredicate ON_DIRT_OR_SAND = BlockPredicate.eitherOf(ON_DIRT, ON_SAND);

		// Terrestria Decorated Features






		entries.add(DENSE_SAKURA_TREES, placeTreeFeature(entries, 3, ON_DIRT, SakuraRoseaConfiguredFeatures.SAKURA_TREE));

		entries.add(DENSE_DARK_SAKURA_TREES, placeTreeFeature(entries, 3, ON_DIRT, SakuraRoseaConfiguredFeatures.DARK_SAKURA_TREE));

		entries.add(DENSE_SAKURA_SHRUBS, placeTreeFeature(entries, 3, ON_DIRT, SakuraRoseaConfiguredFeatures.SAKURA_SHRUB));

	}

	private static DataPool.Builder<BlockState> createStatePoolBuilder() {
		return DataPool.builder();
	}

	public static RegistryKey<PlacedFeature> createRegistryKey(String name) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(SakuraRosea.MOD_ID, name));
	}

	public static PlacedFeature placeTreeFeature(FabricDynamicRegistryProvider.Entries entries, int count, BlockPredicate predicate, RegistryKey<ConfiguredFeature<?, ?>> feature) {
		return placeFeature(entries, feature,
				PlacedFeatures.createCountExtraModifier(count, 0.1f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(predicate));
	}

	public static PlacedFeature placeTreeFeature(FabricDynamicRegistryProvider.Entries entries, int count, int maxWaterDepth, BlockPredicate predicate, RegistryKey<ConfiguredFeature<?, ?>> feature) {
		return placeFeature(entries, feature,
				PlacedFeatures.createCountExtraModifier(count, 0.1f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
				SurfaceWaterDepthFilterPlacementModifier.of(maxWaterDepth),
				BlockFilterPlacementModifier.of(predicate));
	}

	private static PlacedFeature placeFeature(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
		List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
		list.add(BiomePlacementModifier.of());
		return placeFeature(entries, feature, list);

	}

	private static PlacedFeature placeFeatureWithoutBiomeFilter(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
		List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
		return placeFeature(entries, feature, list);
	}

	private static PlacedFeature placeFeature(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> list) {
		return new PlacedFeature(entries.ref(feature), list);
	}

	public static void init() {
		// This just creates the registry keys.  Placed Features are requested and consumed by datagen now.
	}
}
