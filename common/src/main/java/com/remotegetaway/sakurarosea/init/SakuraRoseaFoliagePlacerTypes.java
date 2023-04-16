package com.remotegetaway.sakurarosea.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.tree.placer.PlacerTypes;
import com.remotegetaway.sakurarosea.SakuraRosea;
import com.remotegetaway.sakurarosea.feature.tree.foliageplacers.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class SakuraRoseaFoliagePlacerTypes {

	public static FoliagePlacerType<CanopyFoliagePlacer> CANOPY;
	public static FoliagePlacerType<DotShrubPlacer> DOT_SHRUB;

	public static FoliagePlacerType<JapaneseCanopyFoliagePlacer> JAPANESE_CANOPY;

	public static FoliagePlacerType<NoneFoliagePlacer> NONE;



	public static void init() {
		CANOPY = register("canopy_foliage_placer", CanopyFoliagePlacer.CODEC);
		DOT_SHRUB = register("dot_shrub_foliage_placer", DotShrubPlacer.CODEC);
		JAPANESE_CANOPY = register("japanese_canopy_foliage_placer", JapaneseCanopyFoliagePlacer.CODEC);
		NONE = register("none_foliage_placer", NoneFoliagePlacer.CODEC);
	}

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, Codec<P> codec) {
		return PlacerTypes.registerFoliagePlacer(new Identifier(SakuraRosea.MOD_ID, name), codec);
	}

}
