package com.remotegetaway.sakurarosea.block;

import com.remotegetaway.sakurarosea.SakuraRosea;
import com.terraformersmc.terraform.leaves.block.ExtendedLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class SakuraRoseaOptiLeavesBlock extends ExtendedLeavesBlock {
	public SakuraRoseaOptiLeavesBlock(Settings settings) {
		super(settings);
	}

	@Override
	public boolean isSideInvisible(BlockState state, BlockState neighborState, Direction offset) {
		return SakuraRosea.getConfigManager().getClientConfig().isOptiLeavesEnabled() && super.isSideInvisible(state, neighborState, offset);
	}
}
