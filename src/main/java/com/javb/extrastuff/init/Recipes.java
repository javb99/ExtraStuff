package com.javb.extrastuff.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	public static void init() {
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ruby), "r", 'r', new ItemStack(Items.redstone));// Ruby
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.rubstone), "rr","rr", 'r', new ItemStack(ModItems.ruby));// Rubstone
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.rubyPickaxe), "rrr", " s ", " s ", 'r', new ItemStack(ModItems.ruby), 's', new ItemStack(Items.stick));// Ruby Pickaxe
	}
}
