package com.javb.extrastuff.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.javb.extrastuff.tileentity.TileEntityRubyUpgrader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerRubyUpgrader extends Container{
	
	private TileEntityRubyUpgrader tileUpgrader;
	private int lastUpgradeTime;
    private int lastBurnTime;
    private int lastFuelBurnTime;
	/** Instance of Slot. */
	private final Slot rubySlot;
	private static final String __OBFID = "CL_00001737";

	public ContainerRubyUpgrader(InventoryPlayer player, TileEntityRubyUpgrader tileUpgrader) {
		this.tileUpgrader = tileUpgrader;
        this.addSlotToContainer(new Slot(tileUpgrader, 0, 25, 35));
        this.rubySlot = this.addSlotToContainer(new SlotRubyFuel(tileUpgrader, 1, 80, 35));
        this.addSlotToContainer(new Slot(tileUpgrader, 2, 130, 35));
        
        int i;

        // Player Inventory
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // Player Hot bar
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.tileUpgrader.currentUpgradeTime);
		crafting.sendProgressBarUpdate(this, 1, this.tileUpgrader.burnTime);
		crafting.sendProgressBarUpdate(this, 2, this.tileUpgrader.currentFuelBurnTime);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			if (this.lastUpgradeTime != this.tileUpgrader.currentUpgradeTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.tileUpgrader.currentUpgradeTime);
			}

			if (this.lastBurnTime != this.tileUpgrader.burnTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.tileUpgrader.burnTime);
			}

			if (this.lastFuelBurnTime != this.tileUpgrader.currentFuelBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.tileUpgrader.currentFuelBurnTime);
			}
		}

		this.lastUpgradeTime = this.tileUpgrader.currentUpgradeTime;
		this.lastBurnTime = this.tileUpgrader.burnTime;
		this.lastFuelBurnTime = this.tileUpgrader.currentFuelBurnTime;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int p_75137_1_, int p_75137_2_)
	{
		if (p_75137_1_ == 0)
		{
			this.tileUpgrader.currentUpgradeTime = p_75137_2_;
		}

		if (p_75137_1_ == 1)
		{
			this.tileUpgrader.burnTime = p_75137_2_;
		}

		if (p_75137_1_ == 2)
		{
			this.tileUpgrader.currentFuelBurnTime = p_75137_2_;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileUpgrader.isUseableByPlayer(player);
	}

	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotIndex != 1 && slotIndex != 0)
            {
                if (TileEntityRubyUpgrader.isMultiSpeedTool(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityRubyUpgrader.isRubyFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= 3 && slotIndex < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= 30 && slotIndex < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}


