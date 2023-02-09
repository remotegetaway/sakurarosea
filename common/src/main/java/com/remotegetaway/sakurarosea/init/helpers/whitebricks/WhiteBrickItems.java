package com.remotegetaway.sakurarosea.init.helpers.whitebricks;

public class WhiteBrickItems {
	public WhiteBrickVariantItems white;


	private WhiteBrickItems() {}

	public static WhiteBrickItems register(String name, WhiteBrickBlocks blocks) {
		WhiteBrickItems items = new WhiteBrickItems();

		items.white = WhiteBrickVariantItems.register(name, blocks.white);


		return items;
	}

}
