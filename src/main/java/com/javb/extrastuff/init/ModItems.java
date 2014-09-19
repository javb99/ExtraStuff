package com.javb.extrastuff.init;

import com.javb.extrastuff.item.ItemES;
import com.javb.extrastuff.item.ItemPickaxe;
import com.javb.extrastuff.item.ItemPickaxeMultiSpeed;
import com.javb.extrastuff.item.ItemRuby;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	
	public static final ItemES ruby = new ItemRuby();
	public static final ItemPickaxeMultiSpeed rubyPickaxe = new ItemPickaxeMultiSpeed();
	public static final ItemES Pickaxe_2x = new ItemPickaxe();
	public static final ItemES Pickaxe_4x = new ItemPickaxe();
	
	public static void init() {
		GameRegistry.registerItem(ruby, "ruby");
		GameRegistry.registerItem(rubyPickaxe, "rubyPickaxe");
		
		GameRegistry.registerItem(Pickaxe_2x, "2x_stone_pickaxe");
		GameRegistry.registerItem(Pickaxe_4x, "4x_stone_pickaxe");

	}
}
