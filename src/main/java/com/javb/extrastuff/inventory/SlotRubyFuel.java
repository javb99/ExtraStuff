package com.javb.extrastuff.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.javb.extrastuff.tileentity.TileEntityRubyUpgrader;

public class SlotRubyFuel extends Slot {

    public SlotRubyFuel(IInventory inventory, int p_i1803_3_, int p_i1803_4_, int p_i1803_5_)
    {
        super(inventory, p_i1803_3_, p_i1803_4_, p_i1803_5_);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack != null ? TileEntityRubyUpgrader.isRubyFuel(itemStack) : false;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
     * case of armor slots)
     */
    public int getSlotStackLimit()
    {
        return 64;
    }
}
