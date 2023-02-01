package com.remotegetaway.sakurarosea.data;

import com.remotegetaway.sakurarosea.SakuraRosea;
import com.remotegetaway.sakurarosea.init.SakuraRoseaItems;
import com.remotegetaway.sakurarosea.init.helpers.StoneItems;
import com.remotegetaway.sakurarosea.init.helpers.StoneVariantItems;
import com.remotegetaway.sakurarosea.init.helpers.WoodItems;
import com.remotegetaway.sakurarosea.tag.SakuraRoseaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.function.Consumer;

public class SakuraRoseaRecipeProvider extends FabricRecipeProvider {
	protected SakuraRoseaRecipeProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {
		// misc. recipes
		new ShapelessRecipeJsonBuilder(RecipeCategory.DECORATIONS, SakuraRoseaItems.SAKURA_SAPLING, 1)
			.input(Items.OAK_SAPLING)
			.input(Items.STICK)
			.criterion("has_bryce_sapling", InventoryChangedCriterion.Conditions.items(SakuraRoseaItems.SAKURA_SAPLING))
			.offerTo(exporter, new Identifier(SakuraRosea.MOD_ID, "sakura_sapling_from_oak_sapling"));


		// wood building block recipes
		generateWood(exporter, SakuraRoseaItems.SAKURA, SakuraRoseaItemTags.SAKURA_LOGS);

	}

	private void generateWood(Consumer<RecipeJsonProvider> exporter, WoodItems woodItem, TagKey<Item> logsTag) {
		if (woodItem.hasBoat()) {
			assert (woodItem.boat != null);  // it's not null; this is just for IDEA
			offerBoatRecipe(exporter, woodItem.boat, woodItem.planks);
			assert (woodItem.chestBoat != null);  // it's not null; this is just for IDEA
			offerChestBoatRecipe(exporter, woodItem.chestBoat, woodItem.boat);
		}

		new ShapelessRecipeJsonBuilder(RecipeCategory.REDSTONE, woodItem.button, 1)
			.group("wooden_button")
			.input(woodItem.planks)
			.criterion("has_planks", InventoryChangedCriterion.Conditions.items(woodItem.planks))
			.offerTo(exporter);

		createDoorRecipe(woodItem.door, Ingredient.ofItems(woodItem.planks))
			.criterion("has_planks", InventoryChangedCriterion.Conditions.items(woodItem.planks))
			.offerTo(exporter);

		createFenceRecipe(woodItem.fence, Ingredient.ofItems(woodItem.planks))
			.criterion("has_planks", InventoryChangedCriterion.Conditions.items(woodItem.planks))
			.offerTo(exporter);

		createFenceGateRecipe(woodItem.fenceGate, Ingredient.ofItems(woodItem.planks))
			.criterion("has_planks", InventoryChangedCriterion.Conditions.items(woodItem.planks))
			.offerTo(exporter);

		offerHangingSignRecipe(exporter, woodItem.hangingSign, woodItem.planks);

		offerPlanksRecipe(exporter, woodItem.planks, logsTag, 4);

		offerPressurePlateRecipe(exporter, woodItem.pressurePlate, woodItem.planks);

		createSignRecipe(woodItem.sign, Ingredient.ofItems(woodItem.planks))
			.criterion("has_planks", InventoryChangedCriterion.Conditions.items(woodItem.planks))
			.offerTo(exporter);

		offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, woodItem.slab, woodItem.planks);

		createStairsRecipe(woodItem.stairs, Ingredient.ofItems(woodItem.planks))
			.criterion("has_planks", InventoryChangedCriterion.Conditions.items(woodItem.planks))
			.offerTo(exporter);

		createTrapdoorRecipe(woodItem.trapdoor, Ingredient.ofItems(woodItem.planks))
			.criterion("has_planks", InventoryChangedCriterion.Conditions.items(woodItem.planks))
			.offerTo(exporter);

		// leaf piles are an optional wood feature
		if (woodItem.hasLeafPile()) {
			assert (woodItem.leafPile != null);  // it's not null; this is just for IDEA
			new ShapedRecipeJsonBuilder(RecipeCategory.DECORATIONS, woodItem.leafPile, 16)
					.pattern("LL")
					.input('L', woodItem.leaves)
					.criterion("has_leaves", InventoryChangedCriterion.Conditions.items(woodItem.leaves))
					.offerTo(exporter);
		}

		// some woodItem with no real wood have wood set to log
		if (woodItem.hasWood()) {
			assert (woodItem.wood != null);  // it's not null; this is just for IDEA
			new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, woodItem.wood, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.input('L', woodItem.log)
				.criterion("has_logs", InventoryChangedCriterion.Conditions.items(woodItem.log))
				.offerTo(exporter);

			assert (woodItem.strippedWood != null);  // it's not null; this is just for IDEA
			new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, woodItem.strippedWood, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.input('L', woodItem.strippedLog)
				.criterion("has_logs", InventoryChangedCriterion.Conditions.items(woodItem.strippedLog))
				.offerTo(exporter);
		}
	}

	private void generateStone(Consumer<RecipeJsonProvider> exporter, StoneItems stoneItem) {
		if (stoneItem.bricks != null) {
			generateStoneVariant(exporter, stoneItem.bricks, stoneItem.plain.full);

			new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, 4)
				.group("bricks")
				.pattern("SS")
				.pattern("SS")
				.input('S', stoneItem.plain.full)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.plain.full))
				.offerTo(exporter);
			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, stoneItem.plain.full);

			offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.slab);
			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.full);
			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.plain.full);

			offerCrackingRecipe(exporter, stoneItem.crackedBricks, stoneItem.bricks.full);
		}
		if (stoneItem.cobblestone != null) {
			generateStoneVariant(exporter, stoneItem.cobblestone, null);
		}
		if (stoneItem.mossyBricks != null) {
			generateStoneVariant(exporter, stoneItem.mossyBricks, null);

			new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyBricks.full, 1)
				.group("mossy_bricks")
				.input(stoneItem.bricks.full)
				.input(SakuraRoseaItemTags.MOSSY_INGREDIENTS)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.bricks.full))
				.offerTo(exporter);
		}
		if (stoneItem.mossyCobblestone != null) {
			generateStoneVariant(exporter, stoneItem.mossyCobblestone, null);

			new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyCobblestone.full, 1)
				.group("mossy_cobblestone")
				.input(stoneItem.cobblestone.full)
				.input(SakuraRoseaItemTags.MOSSY_INGREDIENTS)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.cobblestone.full))
				.offerTo(exporter);
		}
		if (stoneItem.plain != null) {
			generateStoneVariant(exporter, stoneItem.plain, null);

			if (stoneItem.cobblestone != null) {
				offerSmelting(exporter,
					Collections.singletonList(stoneItem.cobblestone.full),
					RecipeCategory.BUILDING_BLOCKS,
					stoneItem.plain.full,
					0.1f, 200, "stone");
			}

			new ShapelessRecipeJsonBuilder(RecipeCategory.REDSTONE, stoneItem.button, 1)
				.group("stone_button")
				.input(stoneItem.plain.full)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.plain.full))
				.offerTo(exporter);

			new ShapedRecipeJsonBuilder(RecipeCategory.REDSTONE, stoneItem.pressurePlate, 1)
				.group("stone_pressure_plate")
				.pattern("SS")
				.input('S', stoneItem.plain.full)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.plain.full))
				.offerTo(exporter);
		}
		if (stoneItem.smooth != null) {
			generateStoneVariant(exporter, stoneItem.smooth, null);

			if (stoneItem.plain != null) {
				offerSmelting(exporter,
					Collections.singletonList(stoneItem.plain.full),
					RecipeCategory.BUILDING_BLOCKS,
					stoneItem.smooth.full,
					0.1f, 200, "stone");
			}
		}
	}

	private void generateStoneVariant(Consumer<RecipeJsonProvider> exporter, StoneVariantItems stoneVariantItem, @Nullable BlockItem cutPlainItem) {
		offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, stoneVariantItem.full);
		createStairsRecipe(stoneVariantItem.stairs, Ingredient.ofItems(stoneVariantItem.full))
			.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneVariantItem.full))
			.offerTo(exporter);  // ?? so lame there is no offerStairsRecipe() !!
		offerWallRecipe(exporter, RecipeCategory.DECORATIONS, stoneVariantItem.wall, stoneVariantItem.full);

		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, stoneVariantItem.full, 2);
		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.stairs, stoneVariantItem.full);
		offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneVariantItem.wall, stoneVariantItem.full);

		if (cutPlainItem != null) {
			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, cutPlainItem, 2);
			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.stairs, cutPlainItem);
			offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneVariantItem.wall, cutPlainItem);
		}
	}

	@Override
	protected Identifier getRecipeIdentifier(Identifier identifier) {
		return new Identifier(SakuraRosea.MOD_ID, identifier.getPath());
	}
}
