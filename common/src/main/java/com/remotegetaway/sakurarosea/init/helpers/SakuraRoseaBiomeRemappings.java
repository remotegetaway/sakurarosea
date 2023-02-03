package com.remotegetaway.sakurarosea.init.helpers;

import com.google.common.collect.ImmutableMap;
import com.terraformersmc.terraform.biomeremapper.api.BiomeRemapperApi;
import com.terraformersmc.terraform.biomeremapper.api.DataVersions;
import com.remotegetaway.sakurarosea.SakuraRosea;

public class SakuraRoseaBiomeRemappings implements BiomeRemapperApi {
	public void init() {
		register(SakuraRosea.MOD_ID, DataVersions.V_1_18_2, ImmutableMap.<String, String>builder()
				.put("sakurarosea:wooded_sakura_hills", "sakurarosea:sakura_forest")
				.build());
	}
}
