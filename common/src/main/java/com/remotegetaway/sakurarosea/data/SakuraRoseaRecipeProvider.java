package com.remotegetaway.sakurarosea.data;

import com.remotegetaway.sakurarosea.SakuraRosea;
import com.remotegetaway.sakurarosea.init.SakuraRoseaItems;

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

	@Override
	protected Identifier getRecipeIdentifier(Identifier identifier) {
		return new Identifier(SakuraRosea.MOD_ID, identifier.getPath());
	}
}
