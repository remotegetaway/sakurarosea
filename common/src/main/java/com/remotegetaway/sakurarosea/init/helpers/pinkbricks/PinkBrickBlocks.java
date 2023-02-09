package com.remotegetaway.sakurarosea.init.helpers.pinkbricks;

import net.minecraft.block.MapColor;

public class PinkBrickBlocks {
	public PinkBrickVariantBlocks pink;

	private PinkBrickBlocks() {}

	public static PinkBrickBlocks register(String name, MapColor color) {
		PinkBrickBlocks blocks = new PinkBrickBlocks();

		blocks.pink = PinkBrickVariantBlocks.register(name, color);

		return blocks;
	}
}
