package com.javb.extrastuff.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCraftingDamaged extends ESBaseItem{

	public ItemCraftingDamaged(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setMaxDamage(100);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
		return false;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		 ItemStack stack = itemStack.copy();

        stack.setItemDamage(stack.getItemDamage() + 1);
        if (stack.getItemDamage() >= stack.getMaxDamage()) {
        	return null;
        }
        stack.stackSize = 1;
        
        return stack;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }
	
}
