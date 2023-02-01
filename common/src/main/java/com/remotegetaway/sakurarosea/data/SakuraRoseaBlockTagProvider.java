package com.remotegetaway.sakurarosea.data;

import com.remotegetaway.sakurarosea.init.SakuraRoseaBlocks;
import com.remotegetaway.sakurarosea.init.helpers.WoodBlocks;
import com.remotegetaway.sakurarosea.tag.SakuraRoseaBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class SakuraRoseaBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	protected SakuraRoseaBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void configure(RegistryWrapper.WrapperLookup registries) {
		// basic block tags

		getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
			.add(SakuraRoseaBlocks.POTTED_DARK_SAKURA_SAPLING)
			.add(SakuraRoseaBlocks.POTTED_SAKURA_SAPLING)
			.add(SakuraRoseaBlocks.POTTED_SAKURA_SHRUB_SAPLING);

		getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
			.add(SakuraRoseaBlocks.DARK_SAKURA_LEAVES)
			.add(SakuraRoseaBlocks.SAKURA_SHRUB_LEAVES);

		getOrCreateTagBuilder(BlockTags.LEAVES)
			.add(SakuraRoseaBlocks.DARK_SAKURA_LEAVES)
			.add(SakuraRoseaBlocks.SAKURA_SHRUB_LEAVES);


		getOrCreateTagBuilder(BlockTags.SAPLINGS)
			.add(SakuraRoseaBlocks.DARK_SAKURA_SAPLING)
			.add(SakuraRoseaBlocks.SAKURA_SAPLING)
			.add(SakuraRoseaBlocks.SAKURA_SHRUB_SAPLING);


		// wood building block tags
		addWood(SakuraRoseaBlockTags.SAKURA_LOGS, SakuraRoseaBlocks.SAKURA);

		getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
			.addTag(SakuraRoseaBlockTags.SAKURA_LOGS);
	}


	private void addWood(TagKey<Block> logTag, WoodBlocks woodBlock) {
		FabricTagBuilder woodBuilder = getOrCreateTagBuilder(logTag);
		woodBuilder
			.add(woodBlock.log)
			.add(woodBlock.strippedLog);

		if (woodBlock.hasWood()) {
			woodBuilder
				.add(woodBlock.wood)
				.add(woodBlock.strippedWood);
		}

		if (woodBlock.hasQuarterLog()) {
			woodBuilder
				.add(woodBlock.quarterLog)
				.add(woodBlock.strippedQuarterLog);
		}

		getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(woodBlock.fenceGate);
		getOrCreateTagBuilder(BlockTags.LEAVES).add(woodBlock.leaves);
		getOrCreateTagBuilder(BlockTags.PLANKS).add(woodBlock.planks);
		getOrCreateTagBuilder(BlockTags.SLABS).add(woodBlock.slab);
		getOrCreateTagBuilder(BlockTags.STAIRS).add(woodBlock.stairs);
		getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(woodBlock.sign);
		getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(woodBlock.wallSign);
		getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(woodBlock.hangingSign);
		getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(woodBlock.wallHangingSign);
		getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(woodBlock.button);
		getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(woodBlock.door);
		getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(woodBlock.fence);
		getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(woodBlock.pressurePlate);
		getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(woodBlock.slab);
		getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(woodBlock.stairs);
		getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(woodBlock.trapdoor);

		// Adding to FENCE_GATES or any WOODEN tag does this for AXE_MINEABLE.
		getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(woodBlock.leaves);
		if (woodBlock.hasLeafPile()) {
			getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(woodBlock.leafPile);
		}
	}
}
