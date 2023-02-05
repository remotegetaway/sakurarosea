package com.remotegetaway.sakurarosea.init.helpers;

import net.minecraft.item.BlockItem;

public class StoneVariantItems {
	public BlockItem full;
	public BlockItem slab;
	public BlockItem stairs;
	public BlockItem wall;

	private StoneVariantItems() {}

	public static StoneVariantItems register(String name, StoneVariantBlocks blocks) {
		return register(name, name, blocks);
	}

	public static StoneVariantItems register(String name, String shapedName, StoneVariantBlocks blocks) {
		StoneVariantItems items = new StoneVariantItems();

		items.full = SakuraRoseaRegistry.registerBlockItem(name, blocks.full);
		items.slab = SakuraRoseaRegistry.registerBlockItem(shapedName + "_slab", blocks.slab);
		items.stairs = SakuraRoseaRegistry.registerBlockItem(shapedName + "_stairs", blocks.stairs);
		items.wall = SakuraRoseaRegistry.registerBlockItem(shapedName + "_wall", blocks.wall);

		return items;
	}
}
