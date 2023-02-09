package com.remotegetaway.sakurarosea.init.helpers.pinkbricks;

import com.remotegetaway.sakurarosea.init.helpers.SakuraRoseaRegistry;
import net.minecraft.item.BlockItem;

public class PinkBrickVariantItems {
	public BlockItem block;
	public BlockItem slab;
	public BlockItem stairs;
	public BlockItem wall;
	public BlockItem door;


	private PinkBrickVariantItems() {}

	public static PinkBrickVariantItems register(String name, PinkBrickVariantBlocks blocks) {
		return register(name, name, blocks);
	}

	public static PinkBrickVariantItems register(String name, String shapedName, PinkBrickVariantBlocks blocks) {
		PinkBrickVariantItems items = new PinkBrickVariantItems();

		items.block = SakuraRoseaRegistry.registerBlockItem(name + "_bricks", blocks.block);
		items.slab = SakuraRoseaRegistry.registerBlockItem(shapedName + "_bricks_slab", blocks.slab);
		items.stairs = SakuraRoseaRegistry.registerBlockItem(shapedName + "_bricks_stairs", blocks.stairs);
		items.wall = SakuraRoseaRegistry.registerBlockItem(shapedName + "_bricks_wall", blocks.wall);
		items.door = SakuraRoseaRegistry.registerBlockItem(shapedName + "_bricks_door", blocks.door);

		return items;
	}
}
