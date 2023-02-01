package com.remotegetaway.sakurarosea.config;

import com.remotegetaway.sakurarosea.init.SakuraRoseaBiomes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.Map;
import java.util.stream.Collectors;

public class SakuraRoseaBiomeConfig {
	private final Map<String, Boolean> biomes;

	SakuraRoseaBiomeConfig() {
		// This is where to set biomes to default disabled if needed (replace "k -> true").
		biomes = SakuraRoseaBiomes.BIOMES.stream().collect(Collectors.toMap(k -> k.getValue().getPath(), k -> true));
	}

	public boolean isBiomeEnabled(String name) {
		return !biomes.containsKey(name) || biomes.get(name);
	}

	public boolean isBiomeEnabled(Identifier identifier) {
		return isBiomeEnabled(identifier.getPath());
	}

	public boolean isBiomeEnabled(RegistryKey<Biome> biomeKey) {
		return isBiomeEnabled(biomeKey.getValue());
	}
}
