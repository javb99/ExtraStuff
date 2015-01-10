package com.javb.extrastuff.init;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.javb.extrastuff.block.BlockES;
import com.javb.extrastuff.block.BlockMultiDropOreES;
import com.javb.extrastuff.block.BlockRubStone;
import com.javb.extrastuff.block.BlockRubicInterface;
import com.javb.extrastuff.block.BlockRubyUpgrader;
import com.javb.extrastuff.block.BlockRubyCraftingTable;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.tileentity.TileEntityRubyUpgrader;
import com.javb.extrastuff.world.gen.feature.WorldGenOre;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModBlocks {
	
	public static final Block rubstone = new BlockRubStone();
	public static final Block rubicInterface = new BlockRubicInterface(false);
	public static final Block rubicInterfaceUsed = new BlockRubicInterface(true);
	public static final Block ruby_ore = new BlockMultiDropOreES("oreRuby");
	public static final Block rubyUpgrader = new BlockRubyUpgrader();
	public static final Block rubyCraftingTable = new BlockRubyCraftingTable("rubyCraftingTable");
	
	public static void init() {
		GameRegistry.registerBlock(rubstone, "rubstone");
		GameRegistry.registerBlock(rubicInterface, "rubicInterface");
		GameRegistry.registerBlock(rubicInterfaceUsed, "rubicInterfaceUsed");
		GameRegistry.registerBlock(ruby_ore, "oreRuby");
		GameRegistry.registerWorldGenerator(new WorldGenOre(ruby_ore, 8, 4, 5, 50), 0);
		GameRegistry.registerBlock(rubyUpgrader, "rubyUpgrader");
		GameRegistry.registerBlock(rubyCraftingTable, "rubyCraftingTable");
		
		GameRegistry.registerTileEntity(TileEntityRubyUpgrader.class, "rubyUpgrader");
	}
}
