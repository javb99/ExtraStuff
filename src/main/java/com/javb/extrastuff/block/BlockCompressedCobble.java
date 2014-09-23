package com.javb.extrastuff.block;

import com.javb.extrastuff.creativetab.CreativeTabES;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockCompressedCobble extends Block{
	public BlockCompressedCobble(String unlocalizedName) {
		super(Material.rock);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(unlocalizedName);
		this.setCreativeTab(CreativeTabES.ExtraStuff_tab);
	}
	
	private void setUnlocalizedName(String unlocalizedName){
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

}
