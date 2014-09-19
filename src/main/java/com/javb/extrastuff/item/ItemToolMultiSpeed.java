package com.javb.extrastuff.item;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToolMultiSpeed extends ItemTool {
	
	private static final String SPEED_TAG = "speed";

	protected ItemToolMultiSpeed(float damageAddition, ToolMaterial material, Set minableBlocks) {
		super(damageAddition, material, minableBlocks);
	}
	public float getDigSpeed(ItemStack itemStack, Block block, int metadata) {
		NBTTagCompound tag = itemStack.getTagCompound();
		if (NBTHelper.hasTag(itemStack, SPEED_TAG)) {
			int speed = NBTHelper.getInt(itemStack, SPEED_TAG);
			return speed;
		}
        //return func_150893_a(itemStack, block);
		//LogHelper.info("called get dig speed");
		return 100;
    }
	
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		NBTHelper.setInteger(itemStack, SPEED_TAG, 4);
		LogHelper.info("pickaxe created");
	}
	
	
	
	public boolean onBlockDestroyed(ItemStack itemStack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
		int curSpeed = NBTHelper.getInt(itemStack, SPEED_TAG);
		
		NBTHelper.setInteger(itemStack, SPEED_TAG, curSpeed + 1);
        return false;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName();	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
}

   