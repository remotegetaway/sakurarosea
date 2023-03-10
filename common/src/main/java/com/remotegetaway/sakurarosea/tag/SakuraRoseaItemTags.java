package com.remotegetaway.sakurarosea.tag;

import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SakuraRoseaItemTags {

	public static final TagKey<Item> SAKURA_LOGS = SakuraRoseaItemTags.of("sakura_logs");
	public static final TagKey<Item> PLANKS_THAT_BURN = SakuraRoseaItemTags.of(Identifier.of("c", "planks_that_burn"));

	public static final TagKey<Item> WHITE_BRICKS = SakuraRoseaItemTags.of("white_bricks");

	public static final TagKey<Item> PINK_BRICKS = SakuraRoseaItemTags.of("pink_bricks");

	@SuppressWarnings("UnnecessaryReturnStatement")
	private SakuraRoseaItemTags() {
		return;
	}

	private static TagKey<Item> of(String path) {
		return SakuraRoseaItemTags.of(Identifier.of(SakuraRosea.MOD_ID, path));
	}

	private static TagKey<Item> of(Identifier id) {
		return TagKey.of(RegistryKeys.ITEM, id);
	}
}
