package com.javb.extrastuff.item.powered;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;

import com.javb.extrastuff.item.ESBaseItem;
import com.javb.extrastuff.reference.Constants;
import com.javb.extrastuff.utility.LogHelper;
import com.javb.extrastuff.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ESBaseEnergyItem extends ESBaseItem implements IEnergyContainerItem{

	public static final String KEY_ENERGY = "Energy";
	
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	public ESBaseEnergyItem(int capacity, int maxReceive, int maxExtract) {
		super();
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
		this.setMaxStackSize(1);
	}
	
	// Item
	@Override
	public double getDurabilityForDisplay(ItemStack itemStack) {
		//LogHelper.info("Get Durability: " + this.getEnergyStored(itemStack) / capacity);
		return (capacity - this.getEnergyStored(itemStack)) / (double) capacity;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack itemStack) {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
		super.addInformation(itemStack, player, list, bool);
		int energy = NBTHelper.getInt(itemStack, ESBaseEnergyItem.KEY_ENERGY);
		list.add("RF: " + energy + "/" + this.getMaxEnergyStored(itemStack));
	}
	
	// IEnergyContainerItem
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger(KEY_ENERGY);
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setInteger(KEY_ENERGY, energy);
		}
		LogHelper.info("receiving Energy: " + energyReceived);
		LogHelper.info("New energy level: " + this.getEnergyStored(container));
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		LogHelper.info("extracting Energy");
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey(KEY_ENERGY)) {
			return 0;
		}
		int energy = container.stackTagCompound.getInteger(KEY_ENERGY);
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setInteger(KEY_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		//LogHelper.info("Energy Stored");
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey(KEY_ENERGY)) {
			return 0;
		}
		return container.stackTagCompound.getInteger(KEY_ENERGY);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return capacity;
	}

}
