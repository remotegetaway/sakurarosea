package com.remotegetaway.sakurarosea.init;

import com.mojang.serialization.Codec;
import com.remotegetaway.sakurarosea.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

// This class exports public feature constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class SakuraRoseaFeatures {

	public static TreeFeature QUARTERED_MEGA_TREE;

	@SuppressWarnings({"rawtypes, unchecked"})
	public static void init() {

		// Super hacky casts, but it works
		QUARTERED_MEGA_TREE = register("quartered_mega_tree", new TreeFeature((Codec) QuarteredMegaTreeConfig.CODEC));
	}

	public static <T extends Feature<FC>, FC extends FeatureConfig> T register(String name, T feature) {
		return Registry.register(Registries.FEATURE, new Identifier(SakuraRosea.MOD_ID, name), feature);
	}
}
