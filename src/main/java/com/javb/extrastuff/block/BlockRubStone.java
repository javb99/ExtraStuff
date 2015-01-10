package com.javb.extrastuff.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import com.javb.extrastuff.init.ModBlocks;
import com.javb.extrastuff.init.ModItems;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.MultiBlockHelper;

public class BlockRubStone extends BlockES{
	
	public BlockRubStone() {
		super();
		this.setBlockName("rubstone");
		this.setHardness(2.0f);
	}
}
