package com.javb.extrastuff.init;

//<<<<<<< HEAD
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

import com.javb.extrastuff.item.ItemES;
//<<<<<<< HEAD
import com.javb.extrastuff.item.ItemPickaxeES;
//=======

//=======
import net.minecraft.item.Item;

import com.javb.extrastuff.item.ItemES;
//>>>>>>> a97776c2580575568011e6d757ab3d208c1919f7
import com.javb.extrastuff.item.ItemPickaxeMultiSpeed;
//>>>>>>> 7233c229818400b9580f82da63c326bdb4b0b9e0
import com.javb.extrastuff.item.ItemRuby;
import com.javb.extrastuff.item.ItemSpadeMultiSpeed;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	
	public static ToolMaterial stonex2 = EnumHelper.addToolMaterial("stonex2", 1, 262, 8.0F, 2.0F, 10);
	public static ToolMaterial stonex4 = EnumHelper.addToolMaterial("stonex4", 2, 524, 16.0F, 4.0F, 20);
	public static ToolMaterial stonex8 = EnumHelper.addToolMaterial("stonex8", 2, 1048, 32.0F, 8.0F, 40);
	public static ToolMaterial stonex16 = EnumHelper.addToolMaterial("stonex16", 3, 2096, 64.0F, 16.0F, 80);
	public static ToolMaterial stonex32 = EnumHelper.addToolMaterial("stonex32", 3, 4192, 128.0F, 32.0F, 160);
	public static ToolMaterial stonex64 = EnumHelper.addToolMaterial("stonex64", 3, 8384, 256.0F, 64.0F, 320);
	
	
	public static final ItemES ruby = new ItemRuby();
//<<<<<<< HEAD
//<<<<<<< HEAD
	public static final ItemPickaxeES Pickaxe_2x = new ItemPickaxeES(stonex2,"Pickaxe_2x");
	public static final ItemPickaxeES Pickaxe_4x = new ItemPickaxeES(stonex4,"Pickaxe_4x");
	public static final ItemPickaxeES Pickaxe_8x = new ItemPickaxeES(stonex8,"Pickaxe_8x");
	public static final ItemPickaxeES Pickaxe_16x = new ItemPickaxeES(stonex16,"Pickaxe_16x");
	public static final ItemPickaxeES Pickaxe_32x = new ItemPickaxeES(stonex8,"Pickaxe_32x");
	public static final ItemPickaxeES Pickaxe_64x = new ItemPickaxeES(stonex16,"Pickaxe_64x");
	public static final ItemPickaxeMultiSpeed rubyPickaxe = new ItemPickaxeMultiSpeed(Item.ToolMaterial.EMERALD, "rubyPickaxe");
	public static final ItemSpadeMultiSpeed rubySpade = new ItemSpadeMultiSpeed(Item.ToolMaterial.EMERALD, "rubySpade");
		


//=======

	public static void init() {
		GameRegistry.registerItem(ruby, "ruby");
		GameRegistry.registerItem(rubyPickaxe, "rubyPickaxe");
		GameRegistry.registerItem(Pickaxe_2x, "2x_stone_pickaxe");
		GameRegistry.registerItem(Pickaxe_4x, "4x_stone_pickaxe");
		GameRegistry.registerItem(Pickaxe_8x, "8x_stone_pickaxe");
		GameRegistry.registerItem(Pickaxe_16x, "16x_stone_pickaxe");
		GameRegistry.registerItem(Pickaxe_32x, "32x_stone_pickaxe");
		GameRegistry.registerItem(Pickaxe_64x, "64x_stone_pickaxe");
		GameRegistry.registerItem(rubySpade, "rubySpade");

//=======
	
	
	
	}
}
