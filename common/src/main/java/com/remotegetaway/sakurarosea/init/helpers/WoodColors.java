package com.remotegetaway.sakurarosea.init.helpers;

import net.minecraft.block.MapColor;

public class WoodColors {
	public static final WoodColors SAKURA;


	static {

		SAKURA = new WoodColors();
		SAKURA.bark = MapColor.SPRUCE_BROWN;
		SAKURA.planks = MapColor.TERRACOTTA_MAGENTA;
		SAKURA.leaves = MapColor.PINK;

	}

	public MapColor bark;
	public MapColor planks;
	public MapColor leaves = MapColor.DARK_GREEN;
}
