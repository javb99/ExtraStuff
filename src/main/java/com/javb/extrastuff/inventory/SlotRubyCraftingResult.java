package com.javb.extrastuff.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class SlotRubyCraftingResult extends SlotCrafting{

	public IInventory rubySlots;
	public EntityPlayer player;
	
	public SlotRubyCraftingResult(EntityPlayer player, IInventory craftMatrix, IInventory inventory2, int slotId, int displayX, int displayY) {
		super(player, craftMatrix, inventory2, slotId, displayX, displayY);
		rubySlots = inventory2;
		this.player = player;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
		super.onPickupFromSlot(player, stack);
		
		for (int i = 1; i < 3; i++) {
	        ItemStack itemstack1 = this.rubySlots.getStackInSlot(i);
	
	        if (itemstack1 != null)
	        {
	            this.rubySlots.decrStackSize(i, 1);
	
	            if (itemstack1.getItem().hasContainerItem(itemstack1))
	            {
	                ItemStack containingStack = itemstack1.getItem().getContainerItem(itemstack1);
	
	                if (containingStack != null && containingStack.isItemStackDamageable() && containingStack.getItemDamage() > containingStack.getMaxDamage())
	                {
	                    MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, containingStack));
	                }
	
	                if (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !this.player.inventory.addItemStackToInventory(containingStack))
	                {
	                    if (this.rubySlots.getStackInSlot(i) == null)
	                    {
	                        this.rubySlots.setInventorySlotContents(i, containingStack);
	                    }
	                    else
	                    {
	                        this.player.dropPlayerItemWithRandomChoice(containingStack, false);
	                    }
	                }
	            }
	        }
		}
	}
	
	
}
