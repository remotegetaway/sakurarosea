package com.remotegetaway.sakurarosea.init.helpers.pinkbricks;

public class PinkBrickItems {

	public PinkBrickVariantItems pink;

	private PinkBrickItems() {}

	public static PinkBrickItems register(String name, PinkBrickBlocks blocks) {
		PinkBrickItems items = new PinkBrickItems();

		items.pink = PinkBrickVariantItems.register(name, blocks.pink);

		return items;
	}

}
