package com.remotegetaway.sakurarosea.init;

import com.remotegetaway.sakurarosea.init.helpers.*;
import com.remotegetaway.sakurarosea.block.sapling.SakuraRoseaSaplingGenerator;
import com.remotegetaway.sakurarosea.init.helpers.pinkbricks.PinkBrickBlocks;
import com.remotegetaway.sakurarosea.init.helpers.whitebricks.WhiteBrickBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

// This class exports public block constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class SakuraRoseaBlocks {

	public static WoodBlocks SAKURA;

	public static LeavesBlock SAKURA_SHRUB_LEAVES;
	public static LeavesBlock DARK_SAKURA_LEAVES;

	public static SaplingBlock SAKURA_SAPLING;
	public static SaplingBlock SAKURA_SHRUB_SAPLING;
	public static SaplingBlock DARK_SAKURA_SAPLING;

	public static FlowerPotBlock POTTED_SAKURA_SAPLING;
	public static FlowerPotBlock POTTED_SAKURA_SHRUB_SAPLING;
	public static FlowerPotBlock POTTED_DARK_SAKURA_SAPLING;

	public static WhiteBrickBlocks WHITE;
	public static PinkBrickBlocks PINK;


	public static void init() {

		// normal trunk
		SAKURA = WoodBlocks.register("sakura", WoodColors.SAKURA);

		// strange leaves
		DARK_SAKURA_LEAVES = SakuraRoseaRegistry.register("dark_sakura_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(MapColor.TERRACOTTA_RED).allowsSpawning(SakuraRoseaBlocks::canSpawnOnLeaves).suffocates(SakuraRoseaBlocks::never).blockVision(SakuraRoseaBlocks::never)));
		SAKURA_SHRUB_LEAVES = SakuraRoseaRegistry.register("sakura_shrub_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).allowsSpawning(SakuraRoseaBlocks::canSpawnOnLeaves).suffocates(SakuraRoseaBlocks::never).blockVision(SakuraRoseaBlocks::never)));

		// Saplings
		AbstractBlock.Settings saplingSettings = AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS);

		DARK_SAKURA_SAPLING = SakuraRoseaRegistry.register("dark_sakura_sapling", new SaplingBlock(new SakuraRoseaSaplingGenerator(() -> SakuraRoseaConfiguredFeatures.DARK_SAKURA_TREE), saplingSettings));
		SAKURA_SAPLING = SakuraRoseaRegistry.register("sakura_sapling", new SaplingBlock(new SakuraRoseaSaplingGenerator(() -> SakuraRoseaConfiguredFeatures.SAKURA_TREE), saplingSettings));
		SAKURA_SHRUB_SAPLING = SakuraRoseaRegistry.register("sakura_shrub_sapling", new SaplingBlock(new SakuraRoseaSaplingGenerator(() -> SakuraRoseaConfiguredFeatures.SAKURA_SHRUB), saplingSettings));

		// potted saplings
		POTTED_SAKURA_SAPLING = SakuraRoseaRegistry.register("potted_sakura_sapling", new FlowerPotBlock(SAKURA_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));
		POTTED_SAKURA_SHRUB_SAPLING = SakuraRoseaRegistry.register("potted_sakura_shrub_sapling", new FlowerPotBlock(SAKURA_SHRUB_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));
		POTTED_DARK_SAKURA_SAPLING = SakuraRoseaRegistry.register("potted_dark_sakura_sapling", new FlowerPotBlock(DARK_SAKURA_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));

		// white bricks
		WHITE = WhiteBrickBlocks.register("white", MapColor.WHITE);
		PINK = PinkBrickBlocks.register("pink", MapColor.PINK);

		addFlammables();
	}

	private static void addFlammables() {
		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();

		flammableRegistry.add(SAKURA_SHRUB_LEAVES, 30, 60);
		flammableRegistry.add(DARK_SAKURA_LEAVES, 30, 60);

	}


	public static boolean never(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}
	public static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
		return type == EntityType.OCELOT || type == EntityType.PARROT;
	}
}
