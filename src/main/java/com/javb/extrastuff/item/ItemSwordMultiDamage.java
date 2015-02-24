package com.javb.extrastuff.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;
import com.javb.extrastuff.item.powered.ESBaseEnergyItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSwordMultiDamage extends ESBaseEnergyItem{

	private final Item.ToolMaterial toolMaterial;
	private float damage;
	
	public ItemSwordMultiDamage(Item.ToolMaterial material, String name) {
		super(100000, 1000, 1000);
		this.setUnlocalizedName(name);
		
		this.setMaxStackSize(1);
		this.toolMaterial = material;
		this.damage = 4.0f + material.getDamageVsEntity();
	}
	
	public void useEnergy(ItemStack itemStack, int multiplier, EntityLivingBase evb) {
		this.extractEnergy(itemStack, 100 * multiplier, evb.worldObj.isRemote);
	}
	
	public float func_150893_a(ItemStack itemStack, Block block) {
        if (block == Blocks.web) {
            return 15.0F;
        } else {
        	// if not one of plants, vine, coral, leaves, or gourd return 1.5f
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }
	
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase evb1, EntityLivingBase evb2) {
        this.useEnergy(itemStack, 1, evb2);
        return true;
    }
    
    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase evb) {
        if ((double)block.getBlockHardness(world, x, y, z) != 0.0D) {
        	this.useEnergy(itemStack, 2, evb);
        }

        return true;
    }
    
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
    public boolean func_150897_b(Block block)
    {
        return block == Blocks.web;
    }
    
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName()
    {
        return this.toolMaterial.toString();
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_)
    {
        return false;
    }
    
    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }
    
}
