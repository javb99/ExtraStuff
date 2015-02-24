package com.javb.extrastuff.item;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.javb.extrastuff.block.BlockES;
import com.javb.extrastuff.init.ModCreativeTabs;
import com.javb.extrastuff.item.powered.ESBaseEnergyItem;
import com.javb.extrastuff.reference.Constants;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToolMultiSpeed extends ESBaseEnergyItem {
	
	private String toolClass;
	private Set minableBlocks;
    protected float efficiencyOnProperMaterial = 4.0F;
    /** Damage versus entities. */
    private float damageVsEntity;
    /** The material this tool is made from. */
    protected Item.ToolMaterial toolMaterial;

	protected ItemToolMultiSpeed(float damageAddition, ToolMaterial material, Set minableBlocks) {
		super(100000, 1000, 1000);
		this.toolMaterial = material;
        this.minableBlocks = minableBlocks;
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        this.damageVsEntity = damageAddition + material.getDamageVsEntity();
		
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
	
	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int metadata) {
		NBTTagCompound tag = itemStack.getTagCompound();
		if (ForgeHooks.isToolEffective(itemStack, block, metadata)) {
			if (NBTHelper.hasTag(itemStack, Constants.SPEED_TAG)) {
				int speed = NBTHelper.getInt(itemStack, Constants.SPEED_TAG);
				return speed;
			}
        }
		return 1.0F;
    }
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		super.onCreated(itemStack, world, player);
		NBTHelper.setInteger(itemStack, Constants.SPEED_TAG, 4);
		NBTHelper.setInteger(itemStack, Constants.MINED_TAG, 0);
		NBTHelper.setInteger(itemStack, Constants.UPGRADE_TAG, 2);
		LogHelper.info("pickaxe created");
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase evb) {
		super.onBlockDestroyed(itemStack, world, block, x, y, z, evb);

		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D) {
			this.useEnergy(itemStack, 1, evb);
		}
		
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
	
	public void useEnergy(ItemStack itemStack, int multiplier, EntityLivingBase evb) {
		this.extractEnergy(itemStack, 100 * multiplier, evb.worldObj.isRemote);
	}
	
	// ItemTool

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase evb1, EntityLivingBase evb2) {
        this.useEnergy(itemStack, 2, evb2);
        return true;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isFull3D() {
        return true;
    }

    public Item.ToolMaterial func_150913_i() {
        return this.toolMaterial;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability() {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName() {
        return this.toolMaterial.toString();
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        //return this.toolMaterial.func_150995_f() == p_82789_2_.getItem() ? true : super.getIsRepairable(p_82789_1_, p_82789_2_);
    	return false;
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", (double)this.damageVsEntity, 0));
        return multimap;
    }

    /*===================================== FORGE START =================================*/
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        int level = super.getHarvestLevel(stack, toolClass);
        if (level == -1 && toolClass != null && toolClass.equals(this.toolClass))
        {
            return this.toolMaterial.getHarvestLevel();
        }
        else
        {
            return level;
        }
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return toolClass != null ? ImmutableSet.of(toolClass) : super.getToolClasses(stack);
    }
    /*===================================== FORGE END =================================*/
	
}

   