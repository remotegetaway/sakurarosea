package com.remotegetaway.sakurarosea.init.helpers;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;

public class WoodItems {
	private final String NAME;
	private final TerraformBoatType BOAT_TYPE;

	public final BlockItem log;
	public final BlockItem quarterLog;
	public final BlockItem wood;
	public final BlockItem leaves;
	public final BlockItem leafPile;
	public final BlockItem planks;
	public final BlockItem slab;
	public final BlockItem stairs;
	public final BlockItem fence;
	public final BlockItem fenceGate;
	public final BlockItem door;
	public final BlockItem button;
	public final BlockItem pressurePlate;
	public final SignItem sign;
	public final HangingSignItem hangingSign;
	public final BlockItem trapdoor;
	public final BlockItem strippedLog;
	public final BlockItem strippedQuarterLog;
	public final BlockItem strippedWood;
	public final TerraformBoatItem boat;
	public final TerraformBoatItem chestBoat;

	private WoodItems(String name, WoodBlocks blocks) {
		this.NAME = name;

		log = SakuraRoseaRegistry.registerBlockItem(name + "_log", blocks.log);
		leaves = SakuraRoseaRegistry.registerBlockItem(name + "_leaves", blocks.leaves);
		if (blocks.hasLeafPile()) {
			leafPile = SakuraRoseaRegistry.registerBlockItem(name + "_leaf_pile", blocks.leafPile);
		} else {
			leafPile = null;
		}
		planks = SakuraRoseaRegistry.registerBlockItem(name + "_planks", blocks.planks);
		slab = SakuraRoseaRegistry.registerBlockItem(name + "_slab", blocks.slab);
		stairs = SakuraRoseaRegistry.registerBlockItem(name + "_stairs", blocks.stairs);
		fence = SakuraRoseaRegistry.registerBlockItem(name + "_fence", blocks.fence);
		fenceGate = SakuraRoseaRegistry.registerBlockItem(name + "_fence_gate", blocks.fenceGate);
		door = SakuraRoseaRegistry.registerBlockItem(name + "_door", blocks.door);
		button = SakuraRoseaRegistry.registerBlockItem(name + "_button", blocks.button);
		pressurePlate = SakuraRoseaRegistry.registerBlockItem(name + "_pressure_plate", blocks.pressurePlate);
		trapdoor = SakuraRoseaRegistry.registerBlockItem(name + "_trapdoor", blocks.trapdoor);
		sign = SakuraRoseaRegistry.register(name + "_sign", new SignItem(new Item.Settings().maxCount(16), blocks.sign, blocks.wallSign));
		hangingSign = SakuraRoseaRegistry.register(name + "_hanging_sign", new HangingSignItem(blocks.hangingSign, blocks.wallHangingSign, new Item.Settings().maxCount(16)));
		strippedLog = SakuraRoseaRegistry.registerBlockItem("stripped_" + name + "_log", blocks.strippedLog);

		BOAT_TYPE = SakuraRoseaBoats.register(name, planks);
		if (BOAT_TYPE != null) {
			boat = (TerraformBoatItem) BOAT_TYPE.getItem();
			chestBoat = (TerraformBoatItem) BOAT_TYPE.getChestItem();
		} else {
			boat = null;
			chestBoat = null;
		}

		if (blocks.hasWood()) {
			wood = SakuraRoseaRegistry.registerBlockItem(name + "_wood", blocks.wood);
			strippedWood = SakuraRoseaRegistry.registerBlockItem("stripped_" + name + "_wood", blocks.strippedWood);
		} else {
			wood = null;
			strippedWood = null;
		}

		if (blocks.hasQuarterLog()) {
			quarterLog = SakuraRoseaRegistry.registerBlockItem(name + "_quarter_log", blocks.quarterLog);
			strippedQuarterLog = SakuraRoseaRegistry.registerBlockItem("stripped_" + name + "_quarter_log", blocks.strippedQuarterLog);
		} else {
			quarterLog = null;
			strippedQuarterLog = null;
		}
	}


	public static WoodItems register(String name, WoodBlocks blocks) {
		WoodItems items = new WoodItems(name, blocks);

		items.addCompostables();
		items.addFuels();

		return items;
	}

	protected void addCompostables() {
		CompostingChanceRegistry compostingRegistry = CompostingChanceRegistry.INSTANCE;
		float LEAVES_CHANCE = compostingRegistry.get(Items.OAK_LEAVES);

		compostingRegistry.add(leaves, LEAVES_CHANCE);
		if (hasLeafPile()) {
			compostingRegistry.add(leafPile, LEAVES_CHANCE);
		}
	}

	protected void addFuels() {
		FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;

		fuelRegistry.add(fence, 300);
		fuelRegistry.add(fenceGate, 300);
	}

	public String getName() {
		return NAME;
	}

	public boolean hasQuarterLog() {
		return (quarterLog != null && strippedQuarterLog != null);
	}

	public boolean hasLeafPile() {
		return (leafPile != null);
	}

	public boolean hasWood() {
		return (wood != null && strippedWood != null);
	}

	public boolean hasBoat() {
		return (BOAT_TYPE != null && boat != null && chestBoat != null);
	}
}
