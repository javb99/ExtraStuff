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
import net.minecraftforge.common.ForgeHooks;

import com.javb.extrastuff.block.BlockES;
import com.javb.extrastuff.init.ModCreativeTabs;
import com.javb.extrastuff.reference.Constants;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToolMultiSpeed extends ItemTool {
	
	private String toolClass;

	protected ItemToolMultiSpeed(float damageAddition, ToolMaterial material, Set minableBlocks) {
		super(damageAddition, material, minableBlocks);
		if (this instanceof ItemPickaxeMultiSpeed)
        {
            this.toolClass = "pickaxe";
            
        }/**
        else if (this instanceof ItemAxeMultiSpeed)
        {
            toolClass = "axe";
        }**/
        else if (this instanceof ItemSpadeMultiSpeed)
        {
            toolClass = "shovel";
        }
		this.setHarvestLevel(toolClass, material.getHarvestLevel());
		this.setCreativeTab(ModCreativeTabs.tabRubyStuff);
	}
	
	public float getEfficiencyOnProper(ItemStack itemStack) {
		if (NBTHelper.hasTag(itemStack, Constants.SPEED_TAG)) {
			int speed = NBTHelper.getInt(itemStack, Constants.SPEED_TAG);
			return speed;
		} else {
			return efficiencyOnProperMaterial;
		}
	}
	
	@Override
	public float func_150893_a(ItemStack itemStack, Block p_150893_2_)
    {
		
        return 1.0F;
    }
	
	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int metadata) {
		NBTTagCompound tag = itemStack.getTagCompound();
		if (ForgeHooks.isToolEffective(itemStack, block, metadata)) {
			getEfficiencyOnProper(itemStack);
        }
		return super.getDigSpeed(itemStack, block, metadata);
    }
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		NBTHelper.setInteger(itemStack, Constants.SPEED_TAG, 4);
		NBTHelper.setInteger(itemStack, Constants.MINED_TAG, 0);
		NBTHelper.setInteger(itemStack, Constants.UPGRADE_TAG, 2);
		LogHelper.info("pickaxe created");
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase p_150894_7_)
    {
		super.onBlockDestroyed(itemStack, world, block, x, y, z, p_150894_7_);

		int curMined = NBTHelper.getInt(itemStack, Constants.MINED_TAG);
		int curUpgrade = NBTHelper.getInt(itemStack, Constants.UPGRADE_TAG);
		if (curUpgrade <= curMined + 1) {
			int curSpeed = NBTHelper.getInt(itemStack, Constants.SPEED_TAG);
			NBTHelper.setInteger(itemStack, Constants.SPEED_TAG, curSpeed + 4);
			NBTHelper.setInteger(itemStack, Constants.UPGRADE_TAG, curUpgrade * 2);
		}
		
		if (block instanceof BlockES) {
			NBTHelper.setInteger(itemStack, Constants.MINED_TAG, curMined + 8);
		} else {
			NBTHelper.setInteger(itemStack, Constants.MINED_TAG, curMined + 1);
		}
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
		super.addInformation(itemStack, player, list, bool);
		int speed = NBTHelper.getInt(itemStack, Constants.SPEED_TAG);
		int mined = NBTHelper.getInt(itemStack, Constants.MINED_TAG);
		int toUpgrade = NBTHelper.getInt(itemStack, Constants.UPGRADE_TAG);
		if (speed == 0) {
			speed = 4;
			NBTHelper.setInteger(itemStack, Constants.SPEED_TAG, speed);
			NBTHelper.setInteger(itemStack, Constants.MINED_TAG, 0);
			NBTHelper.setInteger(itemStack, Constants.UPGRADE_TAG, 2);
		}
		list.add("Speed: " + speed);
		list.add("Total Mined: " + mined);
		list.add("Till Next Upgrade: " + (toUpgrade - mined));
		
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

   