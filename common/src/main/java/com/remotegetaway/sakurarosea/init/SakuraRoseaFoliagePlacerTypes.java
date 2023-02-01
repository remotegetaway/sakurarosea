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
	public static FoliagePlacerType<PredictiveSpruceFoliagePlacer> PREDICTIVE_SPRUCE;
	public static FoliagePlacerType<JapaneseCanopyFoliagePlacer> JAPANESE_CANOPY;
	public static FoliagePlacerType<SmallCanopyFoliagePlacer> SMALL_CANOPY;
	public static FoliagePlacerType<NoneFoliagePlacer> NONE;
	public static FoliagePlacerType<SmallLogSphereFoliagePlacer> SMALL_LOG_SPHERE;
	public static FoliagePlacerType<SphereFoliagePlacer> SPHERE;

	public static void init() {
		CANOPY = register("canopy_foliage_placer", CanopyFoliagePlacer.CODEC);
		DOT_SHRUB = register("dot_shrub_foliage_placer", DotShrubPlacer.CODEC);
		PREDICTIVE_SPRUCE = register("predictive_spruce", PredictiveSpruceFoliagePlacer.CODEC);
		JAPANESE_CANOPY = register("japanese_canopy_foliage_placer", JapaneseCanopyFoliagePlacer.CODEC);
		SMALL_CANOPY = register("small_canopy_foliage_placer", SmallCanopyFoliagePlacer.CODEC);
		NONE = register("none_foliage_placer", NoneFoliagePlacer.CODEC);
		SMALL_LOG_SPHERE = register("small_log_sphere_foliage_placer", SmallLogSphereFoliagePlacer.CODEC);
		SPHERE = register("sphere_foliage_placer", SphereFoliagePlacer.CODEC);
	}

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, Codec<P> codec) {
		return PlacerTypes.registerFoliagePlacer(new Identifier(SakuraRosea.MOD_ID, name), codec);
	}

}
