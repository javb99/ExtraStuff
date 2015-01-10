package com.javb.extrastuff.block;

import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import com.javb.extrastuff.init.ModBlocks;
import com.javb.extrastuff.init.ModItems;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.MultiBlockHelper;

public class BlockRubicInterface extends BlockRubStone{
	
	public BlockRubicInterface(boolean used) {
		super();
		if (used) {
			this.setBlockName("rubicInterfaceUsed");
		} else {
			this.setBlockName("rubicInterface");
		}
		this.setHardness(2.0f);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {		
		ItemStack itemStack;
		
		LogHelper.info("rubic interface clicked");
		if (!world.isRemote) {
			if (world.getBlock(x, y, z).equals(ModBlocks.rubicInterface)) {
				if (MultiBlockHelper.isRubyPyramid(world, x, y, z, 1)) {
					ArrayList<EntityItem> entities = (ArrayList) world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+2, z+1));
					for (EntityItem entity : entities) {
						itemStack = entity.getEntityItem();
						 if (itemStack.getItem().equals(Items.nether_star)) {
							for (int i = 0; i < itemStack.stackSize; i++) {
								EntityItem entityItem = new EntityItem(world, x + .5, y+1.5, z + .5, new ItemStack(ModItems.rubicCrystal));
								entityItem.motionX = 0;
								entityItem.motionY = 0;
								entityItem.motionZ = 0;
								world.spawnEntityInWorld(entityItem);
							}
							world.removeEntity(entity);
							world.setBlock(x, y, z, ModBlocks.rubicInterfaceUsed);
						}
					}
				} else {
	        		player.addChatMessage(new ChatComponentTranslation("[Extra Stuff] Not valid multi-block structure!"));
	        	}
			} else {
				player.addChatMessage(new ChatComponentTranslation("[Extra Stuff] Rubic Interface can only be used once!"));
			}
		}
		
		return true;
	}
	
	
}
