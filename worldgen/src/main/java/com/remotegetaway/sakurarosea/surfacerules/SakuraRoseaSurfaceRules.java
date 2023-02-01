package com.remotegetaway.sakurarosea.surfacerules;

import com.mojang.serialization.Codec;
import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class SakuraRoseaSurfaceRules {

	private static MaterialRule block(Block block) {
		return MaterialRules.block(block.getDefaultState());
	}


	public static void init() {
	}

	public static <T extends MaterialRule> Codec<T> register(String id, Codec<T> ruleCodec) {
		return Registry.register(Registries.MATERIAL_RULE, new Identifier(SakuraRosea.MOD_ID, id), ruleCodec);
	}
}
