package com.javb.extrastuff.init;

//<<<<<<< HEAD
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
//=======
//>>>>>>> a97776c2580575568011e6d757ab3d208c1919f7
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	public static void init() {
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ruby), "rrr", "rdr", "rrr", 'r', new ItemStack(Blocks.redstone_block), 'd', new ItemStack(Items.diamond));// Ruby
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.rubstone), "rr","rr", 'r', new ItemStack(ModItems.ruby));// Rubstone
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.rubyPickaxe), "rrr", " s ", " s ", 'r', new ItemStack(ModItems.ruby), 's', new ItemStack(Items.stick));// Ruby Pickaxe
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Pickaxe_2x), "ccc", " s ", " s ", 'c', new ItemStack(ModBlock.Cobble_2x), 's', new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Pickaxe_4x), "ccc", " s ", " s ", 'c', new ItemStack(ModBlock.Cobble_4x), 's', new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Pickaxe_8x), "ccc", " s ", " s ", 'c', new ItemStack(ModBlock.Cobble_8x), 's', new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Pickaxe_16x), "ccc", " s ", " s ", 'c', new ItemStack(ModBlock.Cobble_16x), 's', new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Pickaxe_32x), "ccc", " s ", " s ", 'c', new ItemStack(ModBlock.Cobble_32x), 's', new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Pickaxe_64x), "ccc", " s ", " s ", 'c', new ItemStack(ModBlock.Cobble_64x), 's', new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.Cobble_2x), "cc","cc", 'c', new ItemStack(Blocks.cobblestone));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.Cobble_4x), "cc","cc", 'c', new ItemStack(ModBlock.Cobble_2x));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.Cobble_8x), "cc","cc", 'c', new ItemStack(ModBlock.Cobble_4x));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.Cobble_16x), "cc","cc", 'c', new ItemStack(ModBlock.Cobble_8x));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.Cobble_32x), "cc","cc", 'c', new ItemStack(ModBlock.Cobble_16x));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlock.Cobble_64x), "cc","cc", 'c', new ItemStack(ModBlock.Cobble_32x));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.cobblestone, 4),new ItemStack(ModBlock.Cobble_2x));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlock.Cobble_2x, 4),new ItemStack(ModBlock.Cobble_4x));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlock.Cobble_4x, 4),new ItemStack(ModBlock.Cobble_8x));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlock.Cobble_8x, 4),new ItemStack(ModBlock.Cobble_16x));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlock.Cobble_16x, 4),new ItemStack(ModBlock.Cobble_32x));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlock.Cobble_32x, 4),new ItemStack(ModBlock.Cobble_64x));
	}
}
