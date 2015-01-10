package com.javb.extrastuff.creativetabs;

import com.javb.extrastuff.init.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ExtraStuffTab extends CreativeTabs{

	public ExtraStuffTab() {
		super("tabExtraStuff");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return ModItems.ruby;
	}

}
