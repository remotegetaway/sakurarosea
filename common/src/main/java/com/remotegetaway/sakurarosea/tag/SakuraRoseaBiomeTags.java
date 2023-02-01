package com.remotegetaway.sakurarosea.tag;

import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public final class SakuraRoseaBiomeTags {
	public static final TagKey<Biome> CANYON_ARCH_HAS_STRUCTURE = SakuraRoseaBiomeTags.of("canyon_arch_has_structure");
	public static final TagKey<Biome> OCEAN_VOLCANO_HAS_STRUCTURE = SakuraRoseaBiomeTags.of("ocean_volcano_has_structure");
	public static final TagKey<Biome> VOLCANO_HAS_STRUCTURE = SakuraRoseaBiomeTags.of("volcano_has_structure");

	@SuppressWarnings("UnnecessaryReturnStatement")
	private SakuraRoseaBiomeTags() {
		return;
	}

	private static TagKey<Biome> of(String path) {
		return SakuraRoseaBiomeTags.of(new Identifier(SakuraRosea.MOD_ID, path));
	}

	private static TagKey<Biome> of(Identifier id) {
		return TagKey.of(RegistryKeys.BIOME, id);
	}
}
