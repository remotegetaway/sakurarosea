package com.remotegetaway.sakurarosea.tag;

import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SakuraRoseaBlockTags {


	public static final TagKey<Block> SAKURA_LOGS = SakuraRoseaBlockTags.of("sakura_logs");

	@SuppressWarnings("UnnecessaryReturnStatement")
	private SakuraRoseaBlockTags() {
		return;
	}

	private static TagKey<Block> of(String path) {
		return SakuraRoseaBlockTags.of(new Identifier(SakuraRosea.MOD_ID, path));
	}

	private static TagKey<Block> of(Identifier id) {
		return TagKey.of(RegistryKeys.BLOCK, id);
	}
}
