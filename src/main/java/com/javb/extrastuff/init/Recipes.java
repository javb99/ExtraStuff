package com.javb.extrastuff.init;

import com.javb.extrastuff.item.crafting.RubicRecipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	public static void init() {
		ItemStack rubicCrystal = new ItemStack(ModItems.rubicCrystal, 1, OreDictionary.WILDCARD_VALUE);
		// items
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ruby), "RRR", "RDR", "RRR", 'R', new ItemStack(Blocks.redstone_block), 'D', new ItemStack(Items.diamond));// Ruby
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.rubyPickaxe), "RRR", " S ", " S ", 'R', new ItemStack(ModItems.ruby), 'S', new ItemStack(Items.stick));// Ruby Pickaxe
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.rubySpade), " R ", " S ", " S ", 'R', new ItemStack(ModItems.ruby), 'S', new ItemStack(Items.stick));// Ruby Pickaxe
		// blocks
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.rubyCraftingTable), Blocks.crafting_table, rubicCrystal); // Ruby Crafting Table
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.rubstone), "RR","RR", 'R', new ItemStack(ModItems.ruby));// Rubstone
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.rubicInterface), "rrr","rbr","rrr", 'r', new ItemStack(ModItems.ruby), 'b', new ItemStack(Blocks.stone_button));// Rubic Interface
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.rubyUpgrader), "RRR","RAR","RRR", 'R', new ItemStack(ModBlocks.rubstone), 'A', new ItemStack(Blocks.anvil));// Ruby Upgrader
	}
}
