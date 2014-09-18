package com.javb.extrastuff.init;

import com.javb.extrastuff.block.BlockES;
import com.javb.extrastuff.block.BlockRubStone;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModBlock {
	
	public static final BlockES rubstone = new BlockRubStone();
	
	public static void init() {
		GameRegistry.registerBlock(rubstone, "rubstone");
	}
}
