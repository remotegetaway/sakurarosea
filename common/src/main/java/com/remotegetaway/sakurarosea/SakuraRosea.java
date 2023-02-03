package com.remotegetaway.sakurarosea;

import com.remotegetaway.sakurarosea.init.*;
import com.remotegetaway.sakurarosea.init.helpers.SakuraRoseaPlacementModifierType;
import com.remotegetaway.sakurarosea.item.SakuraRoseaItemGroups;
import com.remotegetaway.sakurarosea.config.SakuraRoseaConfigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class SakuraRosea implements ModInitializer {
	public static final String MOD_ID = "sakurarosea";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	private static final SakuraRoseaConfigManager CONFIG_MANAGER = new SakuraRoseaConfigManager();

	private static Boolean initialized = false;
	private static final ArrayList<Runnable> runnables = new ArrayList<>(1);

	private static void register() {

		SakuraRoseaBlocks.init();
		SakuraRoseaItems.init();
		SakuraRoseaPlacementModifierType.init();
		SakuraRoseaFoliagePlacerTypes.init();
		SakuraRoseaTrunkPlacerTypes.init();
		SakuraRoseaTreeDecorators.init();
		SakuraRoseaFeatures.init();
		SakuraRoseaConfiguredFeatures.init();
		SakuraRoseaPlacedFeatures.init();
		SakuraRoseaBiomes.init();
		SakuraRoseaItemGroups.init();
	}

	@Override
	public void onInitialize() {
		register();

		// This must be after TraverseBiomes.register()
		CONFIG_MANAGER.getBiomeConfig();

		if (!FabricLoader.getInstance().isModLoaded("sakurarosea-worldgen")) {
			SakuraRosea.LOGGER.info("No Sakura Rosea worldgen module present; biome will not generate.");
		}

		// At this point Sakura Rosea is completely initialized.
		initialized = true;
		for (Runnable callback : runnables) {
			callback.run();
		}
	}

	public static void callbackWhenInitialized(Runnable callback) {
		if (initialized) {
			callback.run();
		} else {
			runnables.add(callback);
		}
	}

	public static SakuraRoseaConfigManager getConfigManager() {
		return CONFIG_MANAGER;
	}
}
