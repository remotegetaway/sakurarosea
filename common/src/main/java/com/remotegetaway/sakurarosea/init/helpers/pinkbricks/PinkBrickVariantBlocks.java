package com.remotegetaway.sakurarosea.init.helpers.pinkbricks;

import com.remotegetaway.sakurarosea.init.helpers.SakuraRoseaRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.SoundEvents;

public class PinkBrickVariantBlocks {
	public Block block;
	public SlabBlock slab;
	public StairsBlock stairs;
	public WallBlock wall;
	public DoorBlock door;

	private PinkBrickVariantBlocks() {}

	public static PinkBrickVariantBlocks register(String name, MapColor color) {
		return register(name, name, color);
	}

	public static PinkBrickVariantBlocks register(String name, String shapedName, MapColor color) {
		PinkBrickVariantBlocks blocks = new PinkBrickVariantBlocks();

		blocks.block = SakuraRoseaRegistry.register(name + "_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(color)));
		blocks.slab = SakuraRoseaRegistry.register(shapedName + "_bricks_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE_SLAB).mapColor(color)));
		blocks.stairs = SakuraRoseaRegistry.register(shapedName + "_bricks_stairs", new StairsBlock(blocks.block.getDefaultState(), FabricBlockSettings.copyOf(Blocks.COBBLESTONE_STAIRS).mapColor(color)));
		blocks.wall = SakuraRoseaRegistry.register(shapedName + "_bricks_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE_WALL).mapColor(color)));
		blocks.door = SakuraRoseaRegistry.register(name + "_bricks_door",
														new DoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_DOOR)
																.mapColor(color), BlockSetType.STONE));

		return blocks;
	}
}
