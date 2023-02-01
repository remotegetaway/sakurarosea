package com.remotegetaway.sakurarosea.data;

import com.remotegetaway.sakurarosea.init.SakuraRoseaBiomes;
import com.remotegetaway.sakurarosea.init.SakuraRoseaConfiguredFeatures;
import com.remotegetaway.sakurarosea.init.SakuraRoseaPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class SakuraRoseaDynamicRegistryProvider extends FabricDynamicRegistryProvider {
	protected SakuraRoseaDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
		SakuraRoseaConfiguredFeatures.populate(entries);
		SakuraRoseaPlacedFeatures.populate(entries);
		SakuraRoseaBiomes.populate(entries);
	}

	@Override
	public String getName() {
		return "Sakura Rosea";
	}
}
