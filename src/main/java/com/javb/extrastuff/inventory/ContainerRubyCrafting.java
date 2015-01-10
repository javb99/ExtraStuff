package com.javb.extrastuff.inventory;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import com.javb.extrastuff.init.ModBlocks;
import com.javb.extrastuff.init.ModItems;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.MultiBlockHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerRubyCrafting extends Container {
	/** The crafting matrix inventory (3x3). */
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	public IInventory extraInput = new InventoryRubyCraftingSlots(this);
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;
	private int lastStackAmount;
	private int crystals;
	private static final String __OBFID = "CL_00001744";

	public ContainerRubyCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		this.worldObj = world;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		// x offset is 24 larger than vanilla
		this.addSlotToContainer(new SlotRubyCraftingResult(inventoryPlayer.player, this.craftMatrix, extraInput, 0, 148, 35));
		this.addSlotToContainer(new Slot(extraInput, 1, 15, 17));
		this.addSlotToContainer(new Slot(extraInput, 2, 15, 53));
		this.bindCraftMatrix(this.craftMatrix);
		this.bindInventory(inventoryPlayer);
		
		this.onCraftMatrixChanged(this.craftMatrix);
	}

	protected void bindCraftMatrix(InventoryCrafting craftMatrix) {
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				// x offset is 32 larger than vanilla
				this.addSlotToContainer(new Slot(this.craftMatrix, x + y * 3, 62 + x * 18, 17 + y * 18));
			}
		}
	}

	protected void bindInventory(InventoryPlayer player) {
		// Inventory
		for (int l = 0; l < 3; ++l) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(player, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
			}
		}

		// Hotbar
		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(player, l, 8 + l * 18, 142));
		}
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);
		ItemStack stack = this.getSlot(0).getStack();
		int stackSize = 0;
		
		if (stack != null) {
			stackSize = stack.stackSize;
		}
		crafting.sendProgressBarUpdate(this, 0, stackSize);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		ItemStack stack = this.getSlot(0).getStack();
		int stackSize = 0;
		
		if (stack != null) {
			stackSize = stack.stackSize;
		}
		
		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			if (this.lastStackAmount != stackSize)
			{
				icrafting.sendProgressBarUpdate(this, 0, stackSize);
			}
		}

		this.lastStackAmount = stackSize;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int var, int value) {
		if (var != 0) {
			if (this.getSlot(0).getHasStack()) {
				this.getSlot(0).getStack().stackSize *= value;
			}
		}
	}
	
	private ItemStack multiplyStack(ItemStack stack, int multiplier) {
		if (stack != null) {
			if (stack.stackSize * multiplier > stack.getMaxStackSize()) {
				stack.stackSize = stack.getMaxStackSize();
			} else {
				stack.stackSize *= multiplier;
			}
			
		}
		return stack;
	}

	/**
	 * returns true if any of the 9 items in the craftMatrix are the item
	 * specified.
	 */
	public boolean inventoryContainsItem(IInventory inventory, Item item) {
		ItemStack itemStack;
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			itemStack = inventory.getStackInSlot(i);
			if (itemStack != null && itemStack.getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}

	private int numOfCrystals(IInventory inventory) {
		int crystals = 0;
		ItemStack itemStack;
		
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			itemStack = inventory.getStackInSlot(i);
			if (itemStack != null) {
				if (!itemStack.isItemStackDamageable() || !(itemStack.getItemDamage() < itemStack.getMaxDamage())) {
					
				} else if (itemStack.isItemEqual(new ItemStack(ModItems.rubicCrystal, 1, itemStack.getItemDamage()))) {
					crystals++;
				} else if (itemStack.isItemEqual(new ItemStack(ModItems.pureRubicCrystal, 1, itemStack.getItemDamage()))) {
					crystals += 2;
				}
			}
		}
		return crystals;
	}
	
	/**
	 * Callback for when the crafting matrix is changed.
	 */
	public void onCraftMatrixChanged(IInventory p_75130_1_) {
		//ItemStack result = doubleStack(CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
		ItemStack result;
		Random random;
		int crystals;		
		
		result = null;
		crystals = numOfCrystals(this.extraInput);
		
		result = CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj);
		if (!this.worldObj.isRemote) {
			random = new Random();
			if (result != null && random.nextInt(5 - crystals) == 0) {
				LogHelper.info("crystals: " + crystals);
				LogHelper.info("multiplied stack");
				result.stackSize *= 2;
				LogHelper.info("result: " + result);
			}
		}
				
		if (inventoryContainsItem(this.craftMatrix, Items.nether_star)) {
			// Any recipe that uses a nether star is disabled through this.
			result = null;
		}
		
		if (crystals < 1) {
			// Doesn't have a rubic crystal in the input slots.
			result = null;
		}
				
		this.extraInput.setInventorySlotContents(0, result);
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!this.worldObj.isRemote) {
			// drops all items in the crafting grid.
			for (int i = 0; i < 9; ++i) {
				ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					player.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
			// drops all items in the extra input.
			for (int i = 0; i < extraInput.getSizeInventory(); ++i) {
				ItemStack itemstack = this.extraInput.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					player.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return this.worldObj.getBlock(this.posX, this.posY, this.posZ) != ModBlocks.rubyCraftingTable ? false : p_75145_1_.getDistanceSq((double) this.posX + 0.5D, (double) this.posY + 0.5D, (double) this.posZ + 0.5D) <= 64.0D;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotId);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// Result Slot
			if (slotId == 0) {
				if (!this.mergeItemStack(itemstack1, 12, 48, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			// Extra input slots
			else if (slotId == 1 || slotId == 2) {
				if (!this.mergeItemStack(itemstack1, 12, 48, true)) {
					return null;
				}
			}
			// Player inventory slot
			else if (slotId >= 12 && slotId < 39) {
				if (!this.mergeItemStack(itemstack1, 39, 48, false)) {
					return null;
				}
			}
			// Player hotbar slot
			else if (slotId >= 39 && slotId < 48) {
				if (!this.mergeItemStack(itemstack1, 12, 39, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 12, 48, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
		return p_94530_2_.inventory != this.extraInput && super.func_94530_a(p_94530_1_, p_94530_2_);
	}
}