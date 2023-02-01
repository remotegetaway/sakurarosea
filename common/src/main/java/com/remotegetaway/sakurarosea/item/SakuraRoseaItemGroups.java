package com.remotegetaway.sakurarosea.item;

import com.terraformersmc.terraform.dirt.DirtBlocks;
import com.remotegetaway.sakurarosea.SakuraRosea;
import com.remotegetaway.sakurarosea.init.SakuraRoseaBlocks;
import com.remotegetaway.sakurarosea.init.SakuraRoseaItems;
import com.remotegetaway.sakurarosea.init.helpers.StoneItems;
import com.remotegetaway.sakurarosea.init.helpers.WoodItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SakuraRoseaItemGroups {
	@SuppressWarnings("unused")
	private static final ItemGroup ITEM_GROUP;
	private static final HashMap<ItemGroup, HashMap<ItemConvertible, ItemGroupEntries>> ITEM_GROUP_ENTRY_MAPS;

	/*
	 * These items are the last Vanilla item of a "similar" type to items we add to Vanilla groups.
	 * Each is used to build a collection of items which will be inserted below the Vanilla item.
	 */
	private static final Item BUILDING_STONE_ITEMS = Items.MOSSY_STONE_BRICK_WALL;
	private static final Item BUILDING_WOOD_ITEMS = Items.MANGROVE_BUTTON;
	private static final Item FUNCTIONAL_SIGN = Items.MANGROVE_HANGING_SIGN;
	private static final Item NATURAL_CACTUS = Items.CACTUS;
	private static final Item NATURAL_DIRT_ITEMS = Items.FARMLAND;
	private static final Item NATURAL_LEAVES = Items.MANGROVE_LEAVES;
	private static final Item NATURAL_LOG = Items.MANGROVE_LOG;
	private static final Item NATURAL_SAPLING = Items.MANGROVE_PROPAGULE;
	private static final Item NATURAL_SAND = Items.RED_SANDSTONE;
	private static final Item NATURAL_STONE = Items.STONE;
	private static final Item NATURAL_TALL_VEGETATION = Items.LARGE_FERN;
	private static final Item NATURAL_VEGETATION = Items.FERN;
	private static final Item TOOLS_BOAT = Items.MANGROVE_CHEST_BOAT;

	static {
		ITEM_GROUP_ENTRY_MAPS = new HashMap<>(8);

		/*
		 * For each Vanilla item group, add the same kinds of items Vanilla adds.
		 * Since Minecraft 1.19.3, items are often in multiple item groups...
		 */

		// BUILDING BLOCKS


		// NATURAL


		// Leaves
		addGroupEntry(SakuraRoseaBlocks.DARK_SAKURA_LEAVES, ItemGroups.NATURAL, NATURAL_LEAVES);
		addGroupEntry(SakuraRoseaBlocks.SAKURA_SHRUB_LEAVES, ItemGroups.NATURAL, NATURAL_LEAVES);


		// Saplings
		addGroupEntry(SakuraRoseaBlocks.DARK_SAKURA_SAPLING, ItemGroups.NATURAL, NATURAL_SAPLING);
		addGroupEntry(SakuraRoseaBlocks.SAKURA_SAPLING, ItemGroups.NATURAL, NATURAL_SAPLING);
		addGroupEntry(SakuraRoseaBlocks.SAKURA_SHRUB_SAPLING, ItemGroups.NATURAL, NATURAL_SAPLING);



		// Add WoodItems
		addWoodEntries(SakuraRoseaItems.SAKURA);

		/*
		 * Add the items configured above to the Vanilla item groups.
		 */
		for (ItemGroup group : ITEM_GROUP_ENTRY_MAPS.keySet()) {
			ItemGroupEvents.modifyEntriesEvent(group).register((content) -> {
				FeatureSet featureSet = content.getEnabledFeatures();
				HashMap<ItemConvertible, ItemGroupEntries> entryMap = ITEM_GROUP_ENTRY_MAPS.get(group);

				for (ItemConvertible relative : entryMap.keySet()) {
					ItemGroupEntries entries = entryMap.get(relative);

					// FAPI does not give us a way to add at a feature-flag-disabled location.
					// So, below we have to adjust for any items which may be disabled.
					if (relative == null) {
						// Target the end of the Item Group
						content.addAll(entries.getCollection());
					} else if (relative.equals(Items.MANGROVE_HANGING_SIGN) && !Items.MANGROVE_HANGING_SIGN.isEnabled(featureSet)) {
						content.addAfter(Items.MANGROVE_SIGN, entries.getCollection());
					} else {
						//Terrestria.LOGGER.warn("About to add to Vanilla Item Group '{}' after Item '{}': '{}'", group.getId(), relative, entries.getCollection().stream().map(ItemStack::getItem).collect(Collectors.toList()));
						content.addAfter(relative, entries.getCollection());
					}
				}
			});
		}


		/*
		 * Also add all the items to Terrestria's own item group.
		 */
		ITEM_GROUP = FabricItemGroup.builder(new Identifier(SakuraRosea.MOD_ID, "items"))
				.displayName(Text.literal("Sakura Rosea"))
				.icon(() -> SakuraRoseaBlocks.SAKURA_SAPLING.asItem().getDefaultStack())
				.entries((enabledFeatures, entries, operatorEnabled) -> {
					ITEM_GROUP_ENTRY_MAPS.values().stream()
							.map(HashMap::values).flatMap(Collection::stream)
							.map(ItemGroupEntries::getCollection).flatMap(Collection::stream)
							.collect(Collectors.groupingByConcurrent(ItemStack::getItem)).keySet().stream()
							.sorted(Comparator.comparing((item) -> item.getName().getString())).forEach(entries::add);
				}).build();
	}

	private static void addDirtEntries(DirtBlocks blocks) {
		// NATURAL

		// Dirt Items
		addGroupEntry(blocks.getGrassBlock(), ItemGroups.NATURAL, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.getPodzol(), ItemGroups.NATURAL, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.getDirtPath(), ItemGroups.NATURAL, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.getDirt(), ItemGroups.NATURAL, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.getFarmland(), ItemGroups.NATURAL, NATURAL_DIRT_ITEMS);
	}

	private static void addStoneEntries(StoneItems items) {
		// BUILDING BLOCKS

		// Stone Items
		addGroupEntry(items.plain.full, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.plain.stairs, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.plain.slab, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.plain.wall, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.pressurePlate, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.button, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.full, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.stairs, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.slab, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.wall, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.full, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.stairs, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.slab, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.wall, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.full, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.stairs, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.slab, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.wall, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.full, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.crackedBricks, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.stairs, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.slab, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.wall, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.chiseledBricks, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.full, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.stairs, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.slab, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.wall, ItemGroups.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);


		// NATURAL

		// Stone Items
		addGroupEntry(items.plain.full, ItemGroups.NATURAL, NATURAL_STONE);
	}

	private static void addWoodEntries(WoodItems items) {
		// BUILDING BLOCKS

		// Wood Items
		addGroupEntry(items.log, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		if (items.hasQuarterLog()) {
			addGroupEntry(items.quarterLog, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		if (items.hasWood()) {
			addGroupEntry(items.wood, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		addGroupEntry(items.strippedLog, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		if (items.hasQuarterLog()) {
			addGroupEntry(items.strippedQuarterLog, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		if (items.hasWood()) {
			addGroupEntry(items.strippedWood, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		addGroupEntry(items.planks, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.stairs, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.slab, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.fence, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.fenceGate, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.door, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.trapdoor, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.pressurePlate, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.button, ItemGroups.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);


		// NATURAL

		// Wood Items
		addGroupEntry(items.log, ItemGroups.NATURAL, NATURAL_LOG);
		if (items.hasQuarterLog()) {
			addGroupEntry(items.quarterLog, ItemGroups.NATURAL, NATURAL_LOG);
			if (items.hasWood()) {
				// At the moment, wood generates naturally on all quartered trees...
				addGroupEntry(items.wood, ItemGroups.NATURAL, NATURAL_LOG);
			}
		}

		// Leaves
		addGroupEntry(items.leaves, ItemGroups.NATURAL, NATURAL_LEAVES);
		if (items.hasLeafPile()) {
			addGroupEntry(items.leafPile, ItemGroups.NATURAL, NATURAL_LEAVES);
		}


		// FUNCTIONAL

		// Wood Items
		addGroupEntry(items.sign, ItemGroups.FUNCTIONAL, FUNCTIONAL_SIGN);
		addGroupEntry(items.hangingSign, ItemGroups.FUNCTIONAL, FUNCTIONAL_SIGN);


		// TOOLS

		// Boats
		if (items.hasBoat()) {
			addGroupEntry(items.boat, ItemGroups.TOOLS, TOOLS_BOAT);
			addGroupEntry(items.chestBoat, ItemGroups.TOOLS, TOOLS_BOAT);
		}
	}

	public static void addGroupEntry(ItemConvertible item, ItemGroup group) {
		// Appends the item to the bottom of the group.
		addGroupEntry(item, group, null);
	}

	public static void addGroupEntry(ItemConvertible item, ItemGroup group, @Nullable ItemConvertible relative) {
		HashMap<ItemConvertible, ItemGroupEntries> entryMap = ITEM_GROUP_ENTRY_MAPS.computeIfAbsent(group, (key) -> new HashMap<>(32));
		ItemGroupEntries entries = entryMap.computeIfAbsent(relative, ItemGroupEntries::empty);
		entries.addItem(item);
	}

	public static void init() { }
}
