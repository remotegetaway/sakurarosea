package com.remotegetaway.sakurarosea.init.helpers;

import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SakuraRoseaRegistry {
	public static BlockItem registerBlockItem(String name, Block block) {
		BlockItem item = new BlockItem(block, new Item.Settings());
		return register(name, item);
	}

	public static <I extends Item> I register(String name, I item) {
		if (item instanceof BlockItem blockItem) {
			blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		}
		return Registry.register(Registries.ITEM, Identifier.of(SakuraRosea.MOD_ID, name), item);
	}

	public static <T extends Block> T register(String name, T block) {
		return Registry.register(Registries.BLOCK, Identifier.of(SakuraRosea.MOD_ID, name), block);
	}
}
