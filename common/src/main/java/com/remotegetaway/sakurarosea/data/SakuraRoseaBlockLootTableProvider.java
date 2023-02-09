package com.remotegetaway.sakurarosea.data;

import com.remotegetaway.sakurarosea.init.SakuraRoseaBlocks;
import com.remotegetaway.sakurarosea.init.helpers.pinkbricks.PinkBrickBlocks;
import com.remotegetaway.sakurarosea.init.helpers.WoodBlocks;
import com.remotegetaway.sakurarosea.init.helpers.whitebricks.WhiteBrickBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.SaplingBlock;

import javax.annotation.Nullable;

public class SakuraRoseaBlockLootTableProvider extends FabricBlockLootTableProvider {
	protected SakuraRoseaBlockLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {

		// simple blocks
		addDrop(SakuraRoseaBlocks.DARK_SAKURA_SAPLING);
		addDrop(SakuraRoseaBlocks.SAKURA_SAPLING);
		addDrop(SakuraRoseaBlocks.SAKURA_SHRUB_SAPLING);

		// wood building blocks
		addWoodDrops(SakuraRoseaBlocks.SAKURA, SakuraRoseaBlocks.SAKURA_SAPLING);

		// stone building blocks
		addStoneDrops(SakuraRoseaBlocks.WHITE);
		addStoneDrops(SakuraRoseaBlocks.PINK);

		// potted things
		addPottedPlantDrops(SakuraRoseaBlocks.POTTED_DARK_SAKURA_SAPLING);
		addPottedPlantDrops(SakuraRoseaBlocks.POTTED_SAKURA_SAPLING);
		addPottedPlantDrops(SakuraRoseaBlocks.POTTED_SAKURA_SHRUB_SAPLING);

		// specialty tree leaves
		addDrop(SakuraRoseaBlocks.DARK_SAKURA_LEAVES, leavesDrops(SakuraRoseaBlocks.DARK_SAKURA_LEAVES, SakuraRoseaBlocks.DARK_SAKURA_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		addDrop(SakuraRoseaBlocks.SAKURA_SHRUB_LEAVES, leavesDrops(SakuraRoseaBlocks.SAKURA_SHRUB_LEAVES, SakuraRoseaBlocks.SAKURA_SHRUB_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
	}


	private void addStoneDrops(WhiteBrickBlocks stoneBlock) {
		addDrop(stoneBlock.white.block);
		addDrop(stoneBlock.white.slab, this::slabDrops);
		addDrop(stoneBlock.white.stairs);
		addDrop(stoneBlock.white.door, this::doorDrops);
		addDrop(stoneBlock.white.wall);
	}

	private void addStoneDrops(PinkBrickBlocks stoneBlock) {
		addDrop(stoneBlock.pink.block);
		addDrop(stoneBlock.pink.slab, this::slabDrops);
		addDrop(stoneBlock.pink.stairs);
		addDrop(stoneBlock.pink.door, this::doorDrops);
		addDrop(stoneBlock.pink.wall);
	}


	private void addWoodDrops(WoodBlocks woodBlock, @Nullable SaplingBlock sapling) {
		addDrop(woodBlock.button);
		addDrop(woodBlock.door, this::doorDrops);
		addDrop(woodBlock.fence);
		addDrop(woodBlock.fenceGate);
		addDrop(woodBlock.hangingSign);
		addDrop(woodBlock.log);
		addDrop(woodBlock.planks);
		addDrop(woodBlock.pressurePlate);
		addDrop(woodBlock.sign);
		addDrop(woodBlock.slab, this::slabDrops);
		addDrop(woodBlock.stairs);
		addDrop(woodBlock.strippedLog);
		addDrop(woodBlock.trapdoor);
		addDrop(woodBlock.wallHangingSign);
		addDrop(woodBlock.wallSign);

		if (woodBlock.hasWood()) {
			addDrop(woodBlock.wood);
			addDrop(woodBlock.strippedWood);
		}

		if (woodBlock.hasQuarterLog()) {
			addDrop(woodBlock.quarterLog);
			addDrop(woodBlock.strippedQuarterLog);
		}

		if (sapling != null) {
			addDrop(woodBlock.leaves, leavesDrops(woodBlock.leaves, sapling, 0.05f, 0.0625f, 0.083333336f, 0.1f));
			if (woodBlock.hasLeafPile()) {
				addDrop(woodBlock.leafPile, leavesDrops(woodBlock.leafPile, sapling, 0.00625f, 0.0078125f, 0.010416667f, 0.0125f));
			}
		}
	}
}
