package com.remotegetaway.sakurarosea;

import com.remotegetaway.sakurarosea.init.SakuraRoseaBlocks;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

// This class is an entrypoint
@Environment(EnvType.CLIENT)
public class SakuraRoseaClient implements ClientModInitializer {
	@SuppressWarnings("unused")
	private static final RenderLayer LEAVES_ITEM_LAYER = TexturedRenderLayers.getEntityCutout();
	private static final RenderLayer GRASS_BLOCK_LAYER = RenderLayer.getCutoutMipped();
	private static final RenderLayer PLANT_BLOCK_LAYER = RenderLayer.getCutout();
	private static final RenderLayer DOOR_BLOCK_LAYER = RenderLayer.getCutout();

	private static final BlockColorProvider FOLIAGE_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor();
	private static final BlockColorProvider GRASS_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
	private static final ItemColorProvider FOLIAGE_ITEM_COLORS =
			(item, layer) -> FoliageColors.getColor(0.5, 1.0);
	private static final ItemColorProvider GRASS_ITEM_COLORS =
			(item, layer) -> GrassColors.getColor(0.5, 1.0);

	@Override
	public void onInitializeClient() {
		// Load the client config if it hasn't been loaded already
		SakuraRosea.getConfigManager().getClientConfig();

		BlockRenderLayerMap.INSTANCE.putBlocks(
				DOOR_BLOCK_LAYER,
				SakuraRoseaBlocks.WHITE.white.door
		);

		BlockRenderLayerMap.INSTANCE.putBlocks(
				DOOR_BLOCK_LAYER,
				SakuraRoseaBlocks.PINK.pink.door
		);

		BlockRenderLayerMap.INSTANCE.putBlocks(
				DOOR_BLOCK_LAYER,
				SakuraRoseaBlocks.SAKURA.door
		);

		BlockRenderLayerMap.INSTANCE.putBlocks(
				PLANT_BLOCK_LAYER,
				// Needs to be transparent because of the log cutout part.
				// TODO: Edit the model so that it can be conditionally transparent like actual leaves!
				// Currently they will always be transparent.
				SakuraRoseaBlocks.SAKURA_SAPLING,
				SakuraRoseaBlocks.SAKURA_SHRUB_SAPLING,
				SakuraRoseaBlocks.DARK_SAKURA_SAPLING
		);

		BlockRenderLayerMap.INSTANCE.putBlocks(
				PLANT_BLOCK_LAYER,
				SakuraRoseaBlocks.POTTED_SAKURA_SAPLING,
				SakuraRoseaBlocks.POTTED_SAKURA_SHRUB_SAPLING,
				SakuraRoseaBlocks.POTTED_DARK_SAKURA_SAPLING
		);


		addSigns(
				SakuraRoseaBlocks.SAKURA.sign,
				SakuraRoseaBlocks.SAKURA.hangingSign
		);


		ColorProviderRegistry.ITEM.register(
				GRASS_ITEM_COLORS
		);
		registerEntityRenderers();
	}

	private void registerEntityRenderers() {
		TerraformBoatClientHelper.registerModelLayers(new Identifier(SakuraRosea.MOD_ID, "sakura"), false);

	}

	@SafeVarargs
	private <S extends AbstractSignBlock> void addSigns(S... signs) {
		for (AbstractSignBlock maybeSign : signs) {
			if (maybeSign instanceof TerraformSignBlock sign) {
				addSignTexture(sign.getTexture());
			} else if(maybeSign instanceof TerraformHangingSignBlock sign) {
				addSignTexture(sign.getTexture());
			} else {
				throw new IllegalArgumentException("Only Terraform API signs may be registered via this method.");
			}
		}
	}

	private void addSignTexture(Identifier texture) {
		SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, texture));
	}

	private void addColoredGrass(Block grass) {
		BlockRenderLayerMap.INSTANCE.putBlock(grass, GRASS_BLOCK_LAYER);
		ColorProviderRegistry.BLOCK.register(GRASS_BLOCK_COLORS, grass);
	}
}
