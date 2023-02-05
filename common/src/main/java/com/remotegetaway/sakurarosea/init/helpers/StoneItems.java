package com.remotegetaway.sakurarosea.init.helpers;


public class StoneItems {
	public StoneVariantItems brick;
	public StoneVariantItems vined;

	private StoneItems() {}

	public static StoneItems register(String name, StoneBlocks blocks) {
		StoneItems items = new StoneItems();

		items.brick = StoneVariantItems.register(name, blocks.brick);
		items.vined = StoneVariantItems.register("vined_" + name, blocks.vined);

		return items;
	}
}
