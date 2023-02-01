package com.remotegetaway.sakurarosea.init.helpers;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class SakuraRoseaBoats {
	public static TerraformBoatType register(String path, BlockItem planks) {
		Identifier typeId = Identifier.of(SakuraRosea.MOD_ID, path);
		Identifier boatId = Identifier.of(SakuraRosea.MOD_ID, path + "_boat");
		Identifier chestId = Identifier.of(SakuraRosea.MOD_ID, path + "_chest_boat");
		RegistryKey<TerraformBoatType> boatKey = TerraformBoatTypeRegistry.createKey(typeId);

		return Registry.register(TerraformBoatTypeRegistry.INSTANCE, typeId,
				new TerraformBoatType.Builder()
						.item(TerraformBoatItemHelper.registerBoatItem(boatId, boatKey, false))
						.chestItem(TerraformBoatItemHelper.registerBoatItem(chestId, boatKey, true))
						.planks(planks)
						.build());
	}

	public static TerraformBoatType registerRaft(String path, BlockItem planks) {
		Identifier typeId = Identifier.of(SakuraRosea.MOD_ID, path);
		Identifier boatId = Identifier.of(SakuraRosea.MOD_ID, path + "_raft");
		Identifier chestId = Identifier.of(SakuraRosea.MOD_ID, path + "_chest_raft");
		RegistryKey<TerraformBoatType> boatKey = TerraformBoatTypeRegistry.createKey(typeId);

		return Registry.register(TerraformBoatTypeRegistry.INSTANCE, typeId,
				new TerraformBoatType.Builder()
						.item(TerraformBoatItemHelper.registerBoatItem(boatId, boatKey, false))
						.chestItem(TerraformBoatItemHelper.registerBoatItem(chestId, boatKey, true))
						.planks(planks)
						.raft()
						.build());
	}
}
