package com.remotegetaway.sakurarosea.init.helpers;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.SoundEvents;

public class StoneBlocks {
	public StoneVariantBlocks plain;
	public StoneVariantBlocks smooth;
	public StoneVariantBlocks cobblestone;
	public StoneVariantBlocks mossyCobblestone;
	public StoneVariantBlocks bricks;
	public StoneVariantBlocks mossyBricks;

	public ButtonBlock button;
	public PressurePlateBlock pressurePlate;
	public Block chiseledBricks;
	public Block crackedBricks;

	private StoneBlocks() {}

	public static StoneBlocks register(String name, MapColor color) {
		StoneBlocks blocks = new StoneBlocks();

		// TODO: Need to differentiate stone and cobblestone, because cobblestone breaks a bit slower!
		blocks.plain = StoneVariantBlocks.register(name, color);
		blocks.smooth = StoneVariantBlocks.register("smooth_" + name, color);
		blocks.cobblestone = StoneVariantBlocks.register(name + "_cobblestone", color);
		blocks.mossyCobblestone = StoneVariantBlocks.register("mossy_" + name + "_cobblestone", color);
		blocks.bricks = StoneVariantBlocks.register(name + "_bricks", name + "_brick", color);
		blocks.mossyBricks = StoneVariantBlocks.register("mossy_" + name + "_bricks", "mossy_" + name + "_brick", color);

		blocks.button = SakuraRoseaRegistry.register(name + "_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.STONE_BUTTON).mapColor(color), 20, false, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON));
		blocks.pressurePlate = SakuraRoseaRegistry.register(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(Blocks.STONE_PRESSURE_PLATE).mapColor(color), SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON));
		blocks.chiseledBricks = SakuraRoseaRegistry.register("chiseled_" + name + "_bricks", new Block(FabricBlockSettings.copyOf(Blocks.STONE).mapColor(color)));
		blocks.crackedBricks = SakuraRoseaRegistry.register("cracked_" + name + "_bricks", new Block(FabricBlockSettings.copyOf(Blocks.STONE).mapColor(color)));

		return blocks;
	}
}
