package com.remotegetaway.sakurarosea.init;

import com.remotegetaway.sakurarosea.SakuraRosea;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class SakuraRoseaPlacedFeatures {

	public static final RegistryKey<PlacedFeature> DENSE_SAKURA_TREES = createRegistryKey("dense_sakura_trees");
	public static final RegistryKey<PlacedFeature> DENSE_DARK_SAKURA_TREES = createRegistryKey("dense_dark_sakura_trees");
	public static final RegistryKey<PlacedFeature> DENSE_SAKURA_SHRUBS = createRegistryKey("dense_sakura_shrubs");

	public static void populate(FabricDynamicRegistryProvider.Entries entries) {
		final BlockPredicate ON_DIRT = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT);

		// Terrestria Decorated Features


		entries.add(DENSE_SAKURA_TREES, placeTreeFeature(entries, 2, ON_DIRT, SakuraRoseaConfiguredFeatures.SAKURA_TREE));

		entries.add(DENSE_DARK_SAKURA_TREES, placeTreeFeature(entries, 2, ON_DIRT, SakuraRoseaConfiguredFeatures.DARK_SAKURA_TREE));

		entries.add(DENSE_SAKURA_SHRUBS, placeTreeFeature(entries, 2, ON_DIRT, SakuraRoseaConfiguredFeatures.SAKURA_SHRUB));

	}

	public static RegistryKey<PlacedFeature> createRegistryKey(String name) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(SakuraRosea.MOD_ID, name));
	}

	public static PlacedFeature placeTreeFeature(FabricDynamicRegistryProvider.Entries entries, int count, BlockPredicate predicate, RegistryKey<ConfiguredFeature<?, ?>> feature) {
		return placeFeature(entries, feature,
				PlacedFeatures.createCountExtraModifier(count, 0.01f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(predicate));
	}

	private static PlacedFeature placeFeature(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
		List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
		list.add(BiomePlacementModifier.of());
		return placeFeature(entries, feature, list);

	}

	private static PlacedFeature placeFeature(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> list) {
		return new PlacedFeature(entries.ref(feature), list);
	}

	public static void init() {
		// This just creates the registry keys.  Placed Features are requested and consumed by datagen now.
	}
}
