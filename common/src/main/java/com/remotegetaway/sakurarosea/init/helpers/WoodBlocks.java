package com.remotegetaway.sakurarosea.init.helpers;

import com.remotegetaway.sakurarosea.init.SakuraRoseaBlocks;
import com.terraformersmc.terraform.leaves.block.LeafPileBlock;
import com.terraformersmc.terraform.leaves.block.TransparentLeavesBlock;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import com.terraformersmc.terraform.wood.block.QuarterLogBlock;
import com.terraformersmc.terraform.wood.block.SmallLogBlock;
import com.terraformersmc.terraform.wood.block.StrippableLogBlock;
import com.remotegetaway.sakurarosea.SakuraRosea;
import com.remotegetaway.sakurarosea.block.SakuraRoseaOptiLeavesBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class WoodBlocks {
	private final String NAME;
	private final WoodColors COLORS;
	private final LogSize SIZE;

	public final Block log;
	public final Block quarterLog;
	public final Block wood;
	public final Block leaves;
	public final LeafPileBlock leafPile;
	public final Block planks;
	public final SlabBlock slab;
	public final StairsBlock stairs;
	public final FenceBlock fence;
	public final FenceGateBlock fenceGate;
	public final DoorBlock door;
	public final ButtonBlock button;
	public final PressurePlateBlock pressurePlate;
	public final TerraformSignBlock sign;
	public final TerraformWallSignBlock wallSign;
	public final TerraformHangingSignBlock hangingSign;
	public final TerraformWallHangingSignBlock wallHangingSign;
	public final TrapdoorBlock trapdoor;
	public final Block strippedLog;
	public final Block strippedQuarterLog;
	public final Block strippedWood;

	private WoodBlocks(String name, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarterLog, boolean usesExtendedLeaves) {
		this.NAME = name;
		this.COLORS = colors;
		this.SIZE = size;

		// register manufactured blocks

		planks = SakuraRoseaRegistry.register(name + "_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).mapColor(colors.planks)));
		slab = SakuraRoseaRegistry.register(name + "_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB).mapColor(colors.planks)));
		stairs = SakuraRoseaRegistry.register(name + "_stairs", new StairsBlock(planks.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).mapColor(colors.planks)));
		fence = SakuraRoseaRegistry.register(name + "_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE).mapColor(colors.planks)));
		fenceGate = SakuraRoseaRegistry.register(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE).mapColor(colors.planks), WoodType.OAK));
		door = SakuraRoseaRegistry.register(name + "_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).mapColor(colors.planks), BlockSetType.OAK));
		button = SakuraRoseaRegistry.register(name + "_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).mapColor(colors.planks), BlockSetType.OAK, 30, true));
		pressurePlate = SakuraRoseaRegistry.register(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE).mapColor(colors.planks), BlockSetType.OAK));
		trapdoor = SakuraRoseaRegistry.register(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).mapColor(colors.planks), BlockSetType.OAK));

		Identifier signTexture = Identifier.of(SakuraRosea.MOD_ID, "entity/signs/" + name);
		sign = SakuraRoseaRegistry.register(name + "_sign", new TerraformSignBlock(signTexture, FabricBlockSettings.copyOf(Blocks.OAK_SIGN).mapColor(colors.planks)));
		wallSign = SakuraRoseaRegistry.register(name + "_wall_sign", new TerraformWallSignBlock(signTexture, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).mapColor(colors.planks)));

		Identifier hangingSignTexture = Identifier.of(SakuraRosea.MOD_ID, "entity/signs/hanging/" + name);
		Identifier hangingSignGuiTexture = Identifier.of(SakuraRosea.MOD_ID, "textures/gui/hanging_signs/" + name);
		hangingSign = SakuraRoseaRegistry.register(name + "_hanging_sign", new TerraformHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN).mapColor(colors.planks).requires(FeatureFlags.UPDATE_1_20)));
		wallHangingSign = SakuraRoseaRegistry.register(name + "_wall_hanging_sign", new TerraformWallHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN).mapColor(colors.planks).requires(FeatureFlags.UPDATE_1_20)));

		// register natural and stripped blocks

		if (usesExtendedLeaves) {
			if (size.equals(LogSize.SMALL)) {
				throw new IllegalArgumentException("Small log trees are not compatible with extended leaves, I'm not sure how you even did this...");
			}
			leaves = SakuraRoseaRegistry.register(name + "_leaves", new SakuraRoseaOptiLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(SakuraRoseaBlocks::canSpawnOnLeaves).suffocates(SakuraRoseaBlocks::never).blockVision(SakuraRoseaBlocks::never)));
		} else {
			if (size.equals(LogSize.SMALL)) {
				leaves = SakuraRoseaRegistry.register(name + "_leaves", new TransparentLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(SakuraRoseaBlocks::canSpawnOnLeaves).suffocates(SakuraRoseaBlocks::never).blockVision(SakuraRoseaBlocks::never)));
			} else {
				leaves = SakuraRoseaRegistry.register(name + "_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(SakuraRoseaBlocks::canSpawnOnLeaves).suffocates(SakuraRoseaBlocks::never).blockVision(SakuraRoseaBlocks::never)));
			}
		}

		if (hasLeafPile) {
			leafPile = SakuraRoseaRegistry.register(name + "_leaf_pile", new LeafPileBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).strength(0.025f, 0.1f).noCollision().sounds(BlockSoundGroup.GRASS).mapColor(colors.leaves)));
		} else {
			leafPile = null;
		}

		if (size.equals(LogSize.SMALL)) {
			// Small logs have neither wood nor quarter logs.
			strippedLog = SakuraRoseaRegistry.register("stripped_" + name + "_log", new SmallLogBlock(leaves, null, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			log = SakuraRoseaRegistry.register(name + "_log", new SmallLogBlock(leaves, () -> strippedLog, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));

			strippedWood = null;
			wood = null;

			strippedQuarterLog = null;
			quarterLog = null;
		} else {
			strippedLog = SakuraRoseaRegistry.register("stripped_" + name + "_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			log = SakuraRoseaRegistry.register(name + "_log", new StrippableLogBlock(() -> strippedLog, colors.planks, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));

			strippedWood = SakuraRoseaRegistry.register("stripped_" + name + "_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			wood = SakuraRoseaRegistry.register(name + "_wood", new StrippableLogBlock(() -> strippedWood, colors.bark, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));

			if (hasQuarterLog) {
				strippedQuarterLog = SakuraRoseaRegistry.register("stripped_" + name + "_quarter_log", new QuarterLogBlock(null, colors.planks, Block.Settings.copy(strippedLog)));
				quarterLog = SakuraRoseaRegistry.register(name + "_quarter_log", new QuarterLogBlock(() -> strippedQuarterLog, colors.planks, Block.Settings.copy(log)));
			} else {
				strippedQuarterLog = null;
				quarterLog = null;
			}
		}
	}

	public static WoodBlocks register(String name, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarteredLog, boolean usesExtendedLeaves) {
		WoodBlocks blocks = new WoodBlocks(name, colors, size, hasLeafPile, hasQuarteredLog, usesExtendedLeaves);

		blocks.addFlammables();

		return blocks;
	}

	public static WoodBlocks register(String name, WoodColors colors, LogSize size) {
		return register(name, colors, size, false, false, false);
	}

	public static WoodBlocks register(String name, WoodColors colors) {
		return register(name, colors, LogSize.NORMAL);
	}

	private void addFlammables() {
		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();

		// manufactured
		flammableRegistry.add(fence, 5, 20);
		flammableRegistry.add(fenceGate, 5, 20);
		flammableRegistry.add(planks, 5, 20);
		flammableRegistry.add(slab, 5, 20);
		flammableRegistry.add(stairs, 5, 20);

		// tree
		flammableRegistry.add(log, 5, 5);
		flammableRegistry.add(strippedLog, 5, 5);
		if (hasWood()) {
			flammableRegistry.add(wood, 5, 5);
			flammableRegistry.add(strippedWood, 5, 5);
		}
		if (hasQuarterLog()) {
			flammableRegistry.add(quarterLog, 5, 5);
			flammableRegistry.add(strippedQuarterLog, 5, 5);
		}

		flammableRegistry.add(leaves, 30, 60);
		if (hasLeafPile()) {
			flammableRegistry.add(leafPile, 30, 60);
		}
	}

	public String getName() {
		return NAME;
	}

	public WoodColors getColors() {
		return COLORS;
	}

	public LogSize getSize() {
		return SIZE;
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

	public enum LogSize {
		NORMAL("normal"),
		SMALL("small");

		private final String name;

		LogSize(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}
