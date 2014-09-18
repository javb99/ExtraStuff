package com.javb.extrastuff.init;

import com.javb.extrastuff.item.ItemES;
import com.javb.extrastuff.item.ItemRuby;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	
	public static final ItemES ruby = new ItemRuby();
	
	public static void init() {
		GameRegistry.registerItem(ruby, "ruby");
	}
}
