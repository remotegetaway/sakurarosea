package com.remotegetaway.sakurarosea;

import com.remotegetaway.sakurarosea.surfacebuilders.SakuraRoseaSurfaceBuilders;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class SakuraRoseaWorldgen implements ModInitializer {

	@Override
	public void onInitialize() {
		SakuraRosea.callbackWhenInitialized(SakuraRoseaSurfaceBuilders::init);

		if (FabricLoader.getInstance().isModLoaded("terrablender")) {
			SakuraRosea.LOGGER.info("Enabling Sakura Rosea's TerraBlender worldgen module.");
		} else {
			SakuraRosea.LOGGER.warn("Sakura Rosea world generation disabled; TerraBlender is not present.");
		}
	}
}
