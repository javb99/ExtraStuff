package com.javb.extrastuff.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryRubyCraftingSlots implements IInventory{

	private ItemStack[] stacks = new ItemStack[3];
	public Container callback;
	
	public InventoryRubyCraftingSlots(Container callback) {
		this.callback = callback;
	}
	
	@Override
	public int getSizeInventory() {
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotId) {
		return stacks[slotId];
	}

	/**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
	@Override
    public ItemStack decrStackSize(int slot, int decr) {
		ItemStack itemstack;
        if (this.stacks[slot] != null) {
            

            if (this.stacks[slot].stackSize <= decr) {
                itemstack = this.stacks[slot];
                this.stacks[slot] = null; 
            } else {
                itemstack = this.stacks[slot].splitStack(decr);

                if (this.stacks[slot].stackSize == 0)
                {
                    this.stacks[slot] = null;
                }
            }
        } else {
            itemstack = null;
        }
        
        if (slot != 0) {
			this.callback.onCraftMatrixChanged(this);
		}
        return itemstack;
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int slotId) {
		// don't drop result slot
		if (slotId == 0) {
			return null;
		}
		if (this.stacks[slotId] != null) {
            ItemStack itemstack = this.stacks[slotId];
            this.stacks[slotId] = null;
            return itemstack;
        } else {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int slotId, ItemStack stack) {
		this.stacks[slotId] = stack;
		if (slotId != 0) {
			this.callback.onCraftMatrixChanged(this);
		}
		
	}

	@Override
	public String getInventoryName() {
		return "inputs";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slotId, ItemStack stack) {
		return true;
	}

}
