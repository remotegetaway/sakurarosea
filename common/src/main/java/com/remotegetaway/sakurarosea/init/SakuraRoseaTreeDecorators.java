package com.remotegetaway.sakurarosea.init;

import com.mojang.serialization.Codec;
import com.remotegetaway.sakurarosea.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.remotegetaway.sakurarosea.mixin.TreeDecoratorTypeAccessor;
import com.remotegetaway.sakurarosea.SakuraRosea;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class SakuraRoseaTreeDecorators {

	public static TreeDecoratorType<DanglingLeavesTreeDecorator> DANGLING_LEAVES;


	public static void init() {
		DANGLING_LEAVES = register("dangling_leaves_tree_decorator", DanglingLeavesTreeDecorator.CODEC);

	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, Codec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, new Identifier(SakuraRosea.MOD_ID, name), TreeDecoratorTypeAccessor.createTreeDecoratorType(codec));
	}
}
