package com.javb.extrastuff.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemPickaxeMultiSpeed extends ItemToolMultiSpeed{
	private static final Set canMine = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail, Blocks.hopper});

	public ItemPickaxeMultiSpeed(Item.ToolMaterial material, String name) {
		super(2.0f, material, Sets.newHashSet(canMine));
		this.setUnlocalizedName(name);
	}
	
	/**
	 * return true if item should be dropped.
	 */
	public boolean func_150897_b(Block block) {
		return block.getMaterial() == Material.rock || block.getMaterial() == Material.anvil || block.getMaterial() == Material.iron || block.getMaterial() == Material.ice || block.getMaterial() == Material.packedIce;
	}
}
