package com.remotegetaway.sakurarosea.data;

import com.remotegetaway.sakurarosea.init.SakuraRoseaItems;
import com.remotegetaway.sakurarosea.init.helpers.WoodItems;
import com.remotegetaway.sakurarosea.tag.SakuraRoseaBlockTags;
import com.remotegetaway.sakurarosea.tag.SakuraRoseaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class SakuraRoseaItemTagProvider extends FabricTagProvider.ItemTagProvider {
	protected SakuraRoseaItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, BlockTagProvider blockTagProvider) {
		super(output, registriesFuture, blockTagProvider);
	}

	@Override
	public void configure(RegistryWrapper.WrapperLookup registries) {
		copy(BlockTags.BUTTONS, ItemTags.BUTTONS);

		copy(BlockTags.DIRT, ItemTags.DIRT);

		copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);

		copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);

		copy(BlockTags.LEAVES, ItemTags.LEAVES);

		copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);

		copy(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);

		copy(BlockTags.PLANKS, ItemTags.PLANKS);

		copy(BlockTags.SAND, ItemTags.SAND);

		copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);

		copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);

		copy(BlockTags.SLABS, ItemTags.SLABS);

		copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);

		copy(BlockTags.STAIRS, ItemTags.STAIRS);

		copy(BlockTags.WALLS, ItemTags.WALLS);

		copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);

		copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);

		copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);

		copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);

		copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);

		copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);

		copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);



		getOrCreateTagBuilder(SakuraRoseaItemTags.PLANKS_THAT_BURN)
			.add(SakuraRoseaItems.SAKURA.planks);



		// wood type tags
		copy(SakuraRoseaBlockTags.SAKURA_LOGS, SakuraRoseaItemTags.SAKURA_LOGS);

		// wood items
		addWood(SakuraRoseaItems.SAKURA);
	}

	private void addWood(WoodItems woodItem) {
		// Add boats if they exist via the WoodItem.
		if (woodItem.boat != null) {
			getOrCreateTagBuilder(ItemTags.BOATS).add(woodItem.boat);
		}
		if (woodItem.chestBoat != null) {
			getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(woodItem.chestBoat);
		}
	}
}
