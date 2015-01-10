package com.javb.extrastuff.block;

import java.util.ArrayList;
import java.util.Random;

import com.javb.extrastuff.init.ModBlocks;
import com.javb.extrastuff.init.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMultiDropOreES extends BlockES {
	public BlockMultiDropOreES(String name) {
		super(Material.rock, name);
		this.setHardness(super.blockHardness + 2);
	}

	public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
		return this == ModBlocks.ruby_ore ? ModItems.ruby : Item.getItemFromBlock(this);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random rand) {
		return 1;
	}

	/**
	 * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
	 * (inclusive).
	 */
	public int quantityDroppedWithBonus(int bonus, Random rand) {
		if (bonus > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, rand, bonus)) {
			int j = rand.nextInt(bonus + 2) - 1;

			if (j < 0) {
				j = 0;
			}

			return this.quantityDropped(rand) * (j + 1);
		} else {
			return this.quantityDropped(rand);
		}
	}

	private Random rand = new Random();

	@Override
	public int getExpDrop(IBlockAccess world, int p_149690_5_, int p_149690_7_) {
		if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this)) {
			int count = 0;

			if (this == ModBlocks.ruby_ore) {
				count = MathHelper.getRandomIntegerInRange(rand, 3, 7);
			}
			return count;
		}
		return 0;
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and
	 * wood.
	 */
	public int damageDropped(int p_149692_1_) {
		return 0;
	}
}
