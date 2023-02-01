package com.remotegetaway.sakurarosea.init;

import com.remotegetaway.sakurarosea.init.helpers.SakuraRoseaRegistry;
import com.remotegetaway.sakurarosea.init.helpers.WoodItems;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;

// This class exports public item constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class SakuraRoseaItems {

	public static WoodItems SAKURA;

	public static BlockItem SAKURA_SHRUB_LEAVES;
	public static BlockItem DARK_SAKURA_LEAVES;


	public static BlockItem SAKURA_SAPLING;
	public static BlockItem SAKURA_SHRUB_SAPLING;
	public static BlockItem DARK_SAKURA_SAPLING;


	public static void init() {

		SAKURA = WoodItems.register("sakura", SakuraRoseaBlocks.SAKURA);
		SAKURA_SHRUB_LEAVES = SakuraRoseaRegistry.registerBlockItem("sakura_shrub_leaves", SakuraRoseaBlocks.SAKURA_SHRUB_LEAVES);
		DARK_SAKURA_LEAVES = SakuraRoseaRegistry.registerBlockItem("dark_sakura_leaves", SakuraRoseaBlocks.DARK_SAKURA_LEAVES);

		SAKURA_SAPLING = SakuraRoseaRegistry.registerBlockItem("sakura_sapling", SakuraRoseaBlocks.SAKURA_SAPLING);
		SAKURA_SHRUB_SAPLING = SakuraRoseaRegistry.registerBlockItem("sakura_shrub_sapling", SakuraRoseaBlocks.SAKURA_SHRUB_SAPLING);
		DARK_SAKURA_SAPLING = SakuraRoseaRegistry.registerBlockItem("dark_sakura_sapling", SakuraRoseaBlocks.DARK_SAKURA_SAPLING);

		addCompostables();
		addFuels();
	}

	private static void addCompostables() {
		CompostingChanceRegistry compostingRegistry = CompostingChanceRegistry.INSTANCE;
		float LEAVES_CHANCE = compostingRegistry.get(Items.OAK_LEAVES);
		float SAPLING_CHANCE = compostingRegistry.get(Items.OAK_SAPLING);

		compostingRegistry.add(DARK_SAKURA_LEAVES, LEAVES_CHANCE);
		compostingRegistry.add(SAKURA_SHRUB_LEAVES, LEAVES_CHANCE);

		compostingRegistry.add(DARK_SAKURA_SAPLING, SAPLING_CHANCE);
		compostingRegistry.add(SAKURA_SAPLING, SAPLING_CHANCE);
		compostingRegistry.add(SAKURA_SHRUB_SAPLING, SAPLING_CHANCE);
	}

	private static void addFuels() {
		FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;

	}
}
