package com.remotegetaway.sakurarosea.data;

import com.remotegetaway.sakurarosea.SakuraRosea;
import com.remotegetaway.sakurarosea.init.SakuraRoseaItems;

import com.remotegetaway.sakurarosea.init.helpers.pinkbricks.PinkBrickItems;
import com.remotegetaway.sakurarosea.init.helpers.whitebricks.WhiteBrickItems;
import com.remotegetaway.sakurarosea.init.helpers.WoodItems;
import com.remotegetaway.sakurarosea.tag.SakuraRoseaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

import static net.minecraft.data.server.recipe.CookingRecipeJsonBuilder.createSmelting;

public class SakuraRoseaRecipeProvider extends FabricRecipeProvider {
	protected SakuraRoseaRecipeProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {

		// MISC

		// pink dye from leaf
		new ShapelessRecipeJsonBuilder(RecipeCategory.BREWING, Items.PINK_DYE, 1)
				.input(SakuraRoseaItems.SAKURA_SAPLING)
				.criterion("has_sapling", InventoryChangedCriterion.Conditions.items(SakuraRoseaItems.SAKURA_SAPLING))
				.offerTo(exporter, new Identifier(SakuraRosea.MOD_ID, "pink_dye_from_sakura_sapling"));

		// magenta dye from leaf
		new ShapelessRecipeJsonBuilder(RecipeCategory.BREWING, Items.MAGENTA_DYE, 1)
				.input(SakuraRoseaItems.DARK_SAKURA_SAPLING)
				.criterion("has_sapling", InventoryChangedCriterion.Conditions.items(SakuraRoseaItems.DARK_SAKURA_SAPLING))
				.offerTo(exporter, new Identifier(SakuraRosea.MOD_ID, "magenta_dye_from_dark_sakura_sapling"));


		// WHITE

		// white clay
		new ShapelessRecipeJsonBuilder(RecipeCategory.MISC, SakuraRoseaItems.WHITE_CLAY_BALL, 2)
				.group("white_clay_ball")
				.input(Items.WHITE_DYE)
				.input(Items.CLAY_BALL)
				.criterion("has_white_dye_and_clay", InventoryChangedCriterion.Conditions.items(Items.CLAY_BALL, Items.WHITE_DYE))
				.offerTo(exporter, new Identifier(SakuraRosea.MOD_ID, "white_clay_craft"));

		// white brick
		createSmelting(Ingredient.ofItems
						(new ItemConvertible[]{SakuraRoseaItems.WHITE_CLAY_BALL}),
						RecipeCategory.MISC,
						SakuraRoseaItems.WHITE_BRICK,
						0.3F, 200)
					.criterion("has_white_clay_ball", conditionsFromItem(SakuraRoseaItems.WHITE_CLAY_BALL))
					.offerTo(exporter);

		// white brick block
		new ShapedRecipeJsonBuilder(RecipeCategory.MISC, SakuraRoseaItems.WHITE.white.block, 1)
				.group("bricks")
				.pattern("cc")
				.pattern("cc")
				.input('c', SakuraRoseaItems.WHITE_BRICK)
				.criterion("has_white_brick", InventoryChangedCriterion.Conditions.items(SakuraRoseaItems.WHITE_BRICK))
				.offerTo(exporter);



		// PINK

		// pink clay
		new ShapelessRecipeJsonBuilder(RecipeCategory.MISC, SakuraRoseaItems.PINK_CLAY_BALL, 2)
					.group("pink_clay_ball")
				.input(Items.PINK_DYE)
				.input(Items.CLAY_BALL)
				.criterion("has_pink_dye_and_clay", InventoryChangedCriterion.Conditions.items(Items.CLAY_BALL, Items.PINK_DYE))
				.offerTo(exporter, new Identifier(SakuraRosea.MOD_ID, "pink_clay_craft"));

		// pink brick
		createSmelting(Ingredient.ofItems
						(new ItemConvertible[]{SakuraRoseaItems.PINK_CLAY_BALL}),
				RecipeCategory.MISC,
				SakuraRoseaItems.PINK_BRICK,
				0.3F, 200)
				.criterion("has_pink_clay_ball", conditionsFromItem(SakuraRoseaItems.PINK_CLAY_BALL))
				.offerTo(exporter);

		// pink brick block
		new ShapedRecipeJsonBuilder(RecipeCategory.MISC, SakuraRoseaItems.PINK.pink.block, 1)
				.group("bricks")
				.pattern("cc")
				.pattern("cc")
				.input('c', SakuraRoseaItems.PINK_BRICK)
				.criterion("has_pink_brick", InventoryChangedCriterion.Conditions.items(SakuraRoseaItems.PINK_BRICK))
				.offerTo(exporter);



		// wood building block recipes
		generateWood(exporter, SakuraRoseaItems.SAKURA, SakuraRoseaItemTags.SAKURA_LOGS);

		// stone building block recipes
		generateStone(exporter, SakuraRoseaItems.WHITE);
		generateStone(exporter, SakuraRoseaItems.PINK);

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

	private void generateStone(Consumer<RecipeJsonProvider> exporter, WhiteBrickItems stoneItem) {

		// WHITE
		offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.white.slab, stoneItem.white.block);

		createStairsRecipe(stoneItem.white.stairs, Ingredient.ofItems(stoneItem.white.block))
				.criterion("has_white_bricks", InventoryChangedCriterion.Conditions.items(stoneItem.white.block))
				.offerTo(exporter);

		createDoorRecipe(stoneItem.white.door, Ingredient.ofItems(stoneItem.white.block))
				.criterion("has_white_bricks", InventoryChangedCriterion.Conditions.items(stoneItem.white.block))
				.offerTo(exporter);

		offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.white.wall, stoneItem.white.block);

		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.white.slab, stoneItem.white.block, 2);
		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.white.stairs, stoneItem.white.block);
		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.white.wall, stoneItem.white.block);
	}

	private void generateStone(Consumer<RecipeJsonProvider> exporter, PinkBrickItems stoneItem) {
		// PINK
		offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.pink.slab, stoneItem.pink.block);

		createStairsRecipe(stoneItem.pink.stairs, Ingredient.ofItems(stoneItem.pink.block))
				.criterion("has_pink_bricks", InventoryChangedCriterion.Conditions.items(stoneItem.pink.block))
				.offerTo(exporter);

		createDoorRecipe(stoneItem.pink.door, Ingredient.ofItems(stoneItem.pink.block))
				.criterion("has_pink_bricks", InventoryChangedCriterion.Conditions.items(stoneItem.pink.block))
				.offerTo(exporter);

		offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.pink.wall, stoneItem.pink.block);

		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.pink.slab, stoneItem.pink.block, 2);
		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.pink.stairs, stoneItem.pink.block);
		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.pink.wall, stoneItem.pink.block);
	}


	@Override
		protected Identifier getRecipeIdentifier (Identifier identifier){
			return new Identifier(SakuraRosea.MOD_ID, identifier.getPath());
		}
}
