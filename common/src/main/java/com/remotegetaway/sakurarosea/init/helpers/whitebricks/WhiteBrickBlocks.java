package com.remotegetaway.sakurarosea.init.helpers.whitebricks;

import net.minecraft.block.*;

public class WhiteBrickBlocks {
	public WhiteBrickVariantBlocks white;


	private WhiteBrickBlocks() {}

	public static WhiteBrickBlocks register(String name, MapColor color) {
		WhiteBrickBlocks blocks = new WhiteBrickBlocks();

		blocks.white = WhiteBrickVariantBlocks.register(name,color);


		return blocks;
	}
}
