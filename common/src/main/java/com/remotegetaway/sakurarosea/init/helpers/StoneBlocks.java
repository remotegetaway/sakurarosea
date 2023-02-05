package com.remotegetaway.sakurarosea.init.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;

public class StoneBlocks {
	public StoneVariantBlocks brick;
	public StoneVariantBlocks vined;

	private StoneBlocks() {}

	public static StoneBlocks register(String name, MapColor color) {
		StoneBlocks blocks = new StoneBlocks();

		blocks.brick = StoneVariantBlocks.register(name, color);
		blocks.vined = StoneVariantBlocks.register("vined_" + name, color);

		return blocks;
	}
}
