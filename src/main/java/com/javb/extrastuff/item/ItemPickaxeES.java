
		package com.javb.extrastuff.item;

		import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import com.javb.extrastuff.creativetab.CreativeTabES;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPickaxeES extends ItemPickaxe{

	public ItemPickaxeES(ToolMaterial toolMaterial, String unlocalizedName ) {
			super(toolMaterial);
			this.setUnlocalizedName(unlocalizedName);
			this.setTextureName(unlocalizedName);
			this.setCreativeTab(CreativeTabES.ExtraStuff_tab);
		}
		
		@Override
		public String getUnlocalizedName() {
			return String.format("item.%s%s", Reference.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
		}
		
		@Override
		public String getUnlocalizedName(ItemStack itemStack) {
			return this.getUnlocalizedName();	
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister iconRegister) {
			itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
		}
		
		
		protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
			return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
		}

	}

		