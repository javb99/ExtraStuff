package com.javb.extrastuff.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemSpadeMultiSpeed extends ItemToolMultiSpeed{
	private static final Set canMine = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});
	
	public ItemSpadeMultiSpeed(Item.ToolMaterial material, String name) {
		super(1.0f, material, canMine);
		this.setUnlocalizedName(name);
	}
	
	public boolean func_150897_b(Block p_150897_1_)
    {
		// if true will drop item.
        return p_150897_1_ == Blocks.snow_layer ? true : p_150897_1_ == Blocks.snow;
    }
}
