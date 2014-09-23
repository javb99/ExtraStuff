package com.javb.extrastuff.init;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.javb.extrastuff.block.BlockCompressedCobble;
import com.javb.extrastuff.block.BlockES;
import com.javb.extrastuff.block.BlockRubStone;

import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModBlock {
	
	
	
	public static final BlockES rubstone = new BlockRubStone();
	public static final BlockCompressedCobble Cobble_2x = new BlockCompressedCobble("Cobble_2x");
	public static final BlockCompressedCobble Cobble_4x = new BlockCompressedCobble("Cobble_4x");
	public static final BlockCompressedCobble Cobble_8x = new BlockCompressedCobble("Cobble_8x");
	public static final BlockCompressedCobble Cobble_16x = new BlockCompressedCobble("Cobble_16x");	
	public static final BlockCompressedCobble Cobble_32x = new BlockCompressedCobble("Cobble_32x");
	public static final BlockCompressedCobble Cobble_64x = new BlockCompressedCobble("Cobble_64x");
	
	public static void init() {
		GameRegistry.registerBlock(rubstone, "rubstone");
		GameRegistry.registerBlock(Cobble_2x, "Cobble_2x");
		GameRegistry.registerBlock(Cobble_4x, "Cobble_4x");
		GameRegistry.registerBlock(Cobble_8x, "Cobble_8x");
		GameRegistry.registerBlock(Cobble_16x, "Cobble_16x");
		GameRegistry.registerBlock(Cobble_32x, "Cobble_32x");
		GameRegistry.registerBlock(Cobble_64x, "Cobble_64x");
	}
}
