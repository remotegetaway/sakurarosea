package com.remotegetaway.sakurarosea.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SakuraRoseaDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(SakuraRoseaDynamicRegistryProvider::new);
		pack.addProvider(SakuraRoseaBiomeTagProvider::new);
		pack.addProvider(SakuraRoseaBlockLootTableProvider::new);
		SakuraRoseaBlockTagProvider blockTagProvider = pack.addProvider(SakuraRoseaBlockTagProvider::new);
		pack.addProvider((output, registries) -> new SakuraRoseaItemTagProvider(output, registries, blockTagProvider));
		pack.addProvider(SakuraRoseaRecipeProvider::new);
	}
}
