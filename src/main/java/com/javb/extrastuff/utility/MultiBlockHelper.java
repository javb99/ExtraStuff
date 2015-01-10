package com.javb.extrastuff.utility;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import com.javb.extrastuff.block.BlockRubStone;
import com.javb.extrastuff.init.ModBlocks;

public class MultiBlockHelper {
	public static boolean isRubyPyramid(World world, int x, int y, int z, int tier) {
		// bottom layer;
		for (int x2 = -3; x2 < 4; x2++) {
			for (int z2 = -3; z2 < 4; z2++) {
				if (!(world.getBlock(x + x2, y -2, z + z2) instanceof BlockRubStone)) {
					return false;
				}
			}
		}
		
		// Middle layer
		for (int x2 = -2; x2 < 3; x2++) {
			for (int z2 = -2; z2 < 3; z2++) {
				if (!(world.getBlock(x + x2, y -1, z + z2) instanceof BlockRubStone)) {
					return false;
				}
			}
		}
		/*
		// Top layer
		// any of these blocks not crating table return false.
		if (	!(world.getBlock(x+1, y, z).equals(Blocks.crafting_table) &&
				world.getBlock(x-1, y, z).equals(Blocks.crafting_table) &&
				world.getBlock(x, y, z+1).equals(Blocks.crafting_table) &&
				world.getBlock(x, y, z-1).equals(Blocks.crafting_table) &&
				world.getBlock(x+1, y, z+1).equals(Blocks.crafting_table) &&
				world.getBlock(x-1, y, z-1).equals(Blocks.crafting_table) &&
				world.getBlock(x+1, y, z-1).equals(Blocks.crafting_table) &&
				world.getBlock(x-1, y, z+1).equals(Blocks.crafting_table))) {
			return false;
		}*/
		
		// Top layer
		for (int x2 = -1; x2 < 2; x2++) {
			for (int z2 = -1; z2 < 2; z2++) {
				if (x2 + z2 != 0) {
					LogHelper.info("top layer block: " + world.getBlock(x + x2, y, z + z2));
					if (!world.getBlock(x + x2, y, z + z2).equals(ModBlocks.rubyCraftingTable)) {
						if (tier > 1 || !world.getBlock(x + x2, y, z + z2).equals(Blocks.crafting_table)) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
}