package com.remotegetaway.sakurarosea.init.helpers;

import com.google.common.collect.ImmutableMap;
import com.terraformersmc.terraform.biomeremapper.api.BiomeRemapperApi;
import com.terraformersmc.terraform.biomeremapper.api.DataVersions;
import com.remotegetaway.sakurarosea.SakuraRosea;

public class SakuraRoseaBiomeRemappings implements BiomeRemapperApi {
	public void init() {
		register(SakuraRosea.MOD_ID, DataVersions.V_1_18_2, ImmutableMap.<String, String>builder()
				.put("sakurarosea:caldera_beach", "sakurarosea:caldera")
				.put("sakurarosea:caldera_foothills", "sakurarosea:caldera")
				.put("sakurarosea:caldera_ridge", "sakurarosea:caldera")
				.put("sakurarosea:canyon_arches", "sakurarosea:canyon")
				.put("sakurarosea:canyon_cliffs", "sakurarosea:canyon")
				.put("sakurarosea:canyon_edge", "sakurarosea:canyon")
				.put("sakurarosea:dense_woodlands_edge", "minecraft:forest")
				.put("sakurarosea:dunes_edge", "minecraft:desert")
				.put("sakurarosea:hemlock_clearing", "sakurarosea:hemlock_treeline")
				.put("sakurarosea:lush_redwood_clearing", "sakurarosea:lush_redwood_forest")
				.put("sakurarosea:lush_redwood_forest_edge", "sakurarosea:lush_redwood_forest")
				.put("sakurarosea:outback_bushland", "sakurarosea:outback")
				.put("sakurarosea:outback_uluru", "sakurarosea:outback")
				.put("sakurarosea:rainbow_rainforest_lake", "sakurarosea:rainbow_rainforest")
				.put("sakurarosea:rainbow_rainforest_mountains", "sakurarosea:rainbow_rainforest")
				.put("sakurarosea:redwood_clearing", "sakurarosea:redwood_forest")
				.put("sakurarosea:redwood_forest_edge", "sakurarosea:redwood_forest")
				.put("sakurarosea:snowy_hemlock_clearing", "sakurarosea:snowy_hemlock_treeline")
				.put("sakurarosea:wooded_cypress_hills", "sakurarosea:cypress_forest")
				.put("sakurarosea:wooded_sakura_hills", "sakurarosea:sakura_forest")
				.put("sakurarosea:wooded_sakura_hills", "sakurarosea:sakura_forest")
				.put("sakurarosea:volcanic_island_beach", "sakurarosea:volcanic_island")
				.put("sakurarosea:volcanic_island_shore", "sakurarosea:volcanic_island")
				.build());
	}
}
