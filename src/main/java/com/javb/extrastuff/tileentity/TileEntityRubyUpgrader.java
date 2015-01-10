package com.javb.extrastuff.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.javb.extrastuff.init.ModBlocks;
import com.javb.extrastuff.init.ModItems;
import com.javb.extrastuff.item.ItemToolMultiSpeed;
import com.javb.extrastuff.reference.Constants;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRubyUpgrader extends ESTileEntity implements ISidedInventory
{

	public int[] topAccessableSlots = new int[] {0};
	public int[] sideAccessableSlots = new int[] {1};
	public int[] bottomAccessableSlots = new int[] {2};
	
	private ItemStack[] upgraderItemStacks = new ItemStack[3];
	// NBT tags
	private String name;
	/**
	 * Ticks that upgrader will keep burning. Decremented each tick.
	 */
	public int burnTime;
	/**
	 * Ticks that a fresh item would burn for.
	 */
	public int currentFuelBurnTime;
	/**
	 * Ticks since upgrade started
	 */
	public int currentUpgradeTime;
	
	public static final int REQUIRED_UPGRADE_TIME = 100;
	
	@Override
	public void updateEntity() {
        //furnace
        boolean flag = this.burnTime > 0;
        boolean dirty = false;

        if (this.burnTime > 0) {
            --this.burnTime;
        }
        
        if (!this.worldObj.isRemote) {
            if (this.burnTime != 0 || this.upgraderItemStacks[1] != null && this.upgraderItemStacks[0] != null) {
                if (this.burnTime == 0 && this.canUpgrade()) {
                    this.currentFuelBurnTime = this.burnTime = getRubyBurnTime(this.upgraderItemStacks[1]);

                    if (this.burnTime > 0) {
                        dirty = true;

                        if (this.upgraderItemStacks[1] != null) {
                            --this.upgraderItemStacks[1].stackSize;

                            if (this.upgraderItemStacks[1].stackSize == 0) {
                                this.upgraderItemStacks[1] = upgraderItemStacks[1].getItem().getContainerItem(upgraderItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canUpgrade()) {
                    ++this.currentUpgradeTime;

                    if (this.currentUpgradeTime == REQUIRED_UPGRADE_TIME) {
                        this.currentUpgradeTime = 0;
                        this.upgrade();
                        dirty = true;
                    }
                }
                else {
                    this.currentUpgradeTime = 0;
                }
            }
            
            if (flag != this.burnTime > 0) {
                dirty = true;
                //BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (dirty) {
            this.markDirty();
        }
	}
	
	/**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getUpgradeProgressScaled(int scale) {
    	//LogHelper.info("ProgressScaled.");
        return this.currentUpgradeTime * scale / REQUIRED_UPGRADE_TIME;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale) {
        if (this.currentFuelBurnTime == 0)
        {
            this.currentFuelBurnTime = 200;
        }
        return this.burnTime * scale / this.currentFuelBurnTime;
    }
	
	private void upgrade() {
		if (canUpgrade()) {
			int curNextUpgrade = NBTHelper.getInt(upgraderItemStacks[0], Constants.UPGRADE_TAG);
			int curMined = NBTHelper.getInt(upgraderItemStacks[0], Constants.MINED_TAG);
			int curSpeed = NBTHelper.getInt(upgraderItemStacks[0], Constants.SPEED_TAG);
			for (int i = 0; i < getRubyBurnTime(upgraderItemStacks[1]); i++) {
				curMined += 1;
				if (curMined >= curNextUpgrade) {
					curSpeed += 4;
					curNextUpgrade *= 2;
				}
				NBTHelper.setInteger(upgraderItemStacks[0], Constants.SPEED_TAG, curSpeed);
				NBTHelper.setInteger(upgraderItemStacks[0], Constants.MINED_TAG, curMined);
				NBTHelper.setInteger(upgraderItemStacks[0], Constants.UPGRADE_TAG, curNextUpgrade);
			}
			// handles removing rubies.
			if (this.upgraderItemStacks[1] != null)
            {
                --this.upgraderItemStacks[1].stackSize;

                if (this.upgraderItemStacks[1].stackSize == 0)
                {
                    this.upgraderItemStacks[1] = upgraderItemStacks[1].getItem().getContainerItem(upgraderItemStacks[1]);
                }
            }
			if (upgraderItemStacks[1] != null) {
				LogHelper.info("#rubys: " + upgraderItemStacks[1].stackSize);
			} else {
				LogHelper.info("Out of rubys.");
			}
			// move tool to output slot.
			upgraderItemStacks[2] = upgraderItemStacks[0];
			upgraderItemStacks[0] = null;
			this.markDirty();
		}
	}
	
	private boolean canUpgrade() {
		if ( upgraderItemStacks[0] != null &&
			 upgraderItemStacks[1] != null &&
			 upgraderItemStacks[2] == null &&
			 isMultiSpeedTool(upgraderItemStacks[0]) &&
			 isRubyFuel(upgraderItemStacks[1]) ){
			return true;
		}
		return false;
	}
	
	public boolean isBurning() {
        return this.burnTime > 0;
    }
	
	public static boolean isMultiSpeedTool(ItemStack itemStack) {
		return itemStack.getItem() instanceof ItemToolMultiSpeed;
	}
	
	public static boolean isRubyFuel(ItemStack itemStack) {
		return getRubyBurnTime(itemStack) > 0;
	}
	
	public static int getRubyBurnTime(ItemStack itemStack) {
		if (itemStack.getItem().equals(ModItems.ruby)) return 16;
		if (itemStack.getItem().equals(Item.getItemFromBlock(ModBlocks.rubstone))) return 64;
		if (itemStack.getItem().equals(ModItems.rubicCrystal)) return 8;
		if (itemStack.getItem().equals(ModItems.pureRubicCrystal)) return 12;
		return 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.upgraderItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.getSizeInventory())
            {
                this.upgraderItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        //extra fields here
        if (nbt.hasKey("UpgradeTime")) {
            this.currentUpgradeTime = nbt.getInteger("UpgradeTime");
        }
        
        if (nbt.hasKey("BurnTime")) {
            this.burnTime = nbt.getInteger("BurnTime");
        }
        this.currentFuelBurnTime = getRubyBurnTime(upgraderItemStacks[1]);
        
        if (nbt.hasKey("CustomName", 8)) {
            this.name = nbt.getString("CustomName");
        }
    }
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            if (this.upgraderItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.upgraderItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.name);
        }
        
        nbt.setInteger("BurnTime", this.burnTime);
        nbt.setInteger("UpgradeTime", this.currentUpgradeTime);
    }
	
	@Override
	public int getSizeInventory() {
		return this.upgraderItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot >= 0 && slot < this.getSizeInventory()) {
			return this.upgraderItemStacks[slot];
		}
		return null;
	}
	
	/**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
	@Override
    public ItemStack decrStackSize(int slot, int decr) {
        if (this.upgraderItemStacks[slot] != null) {
            ItemStack itemstack;

            if (this.upgraderItemStacks[slot].stackSize <= decr) {
                itemstack = this.upgraderItemStacks[slot];
                this.upgraderItemStacks[slot] = null;
                return itemstack;
            } else {
                itemstack = this.upgraderItemStacks[slot].splitStack(decr);

                if (this.upgraderItemStacks[slot].stackSize == 0)
                {
                    this.upgraderItemStacks[slot] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }
    
    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (slot >= 0 && slot < this.upgraderItemStacks.length && this.upgraderItemStacks[slot] != null) {
            ItemStack itemstack = this.upgraderItemStacks[slot];
            this.upgraderItemStacks[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.upgraderItemStacks[slot] = itemStack;

        if (itemStack != null && slot >= 0 && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : "container." + Reference.MODID + ":rubyUpgrader";
	}

	@Override
	public boolean hasCustomInventoryName() {
		 return this.name != null && this.name.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;

	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		if (slot == 0) {
			return itemStack.getItem() instanceof ItemToolMultiSpeed;
		} else if (slot == 1) {
			return isRubyFuel(itemStack);
		} else {
			return false;
		}
    }
	
	// ISidedInventory
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if (side == Constants.TOP) {
			return this.topAccessableSlots;
		} else if (side == Constants.BOTTOM) {
			return this.bottomAccessableSlots;
		}
		return this.sideAccessableSlots;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
		return isItemValidForSlot(slot, itemStack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		return side == Constants.BOTTOM && slot == 2;
	}
   
}
