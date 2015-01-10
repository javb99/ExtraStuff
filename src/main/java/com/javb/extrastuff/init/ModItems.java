package com.javb.extrastuff.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSimpleFoiled;

import com.javb.extrastuff.item.ItemCraftingDamaged;
import com.javb.extrastuff.item.ItemES;
import com.javb.extrastuff.item.ItemPickaxeMultiSpeed;
import com.javb.extrastuff.item.ItemRuby;
import com.javb.extrastuff.item.ItemSpadeMultiSpeed;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	
	public static final ItemES ruby = new ItemRuby();
	public static final Item rubicCrystal = new ItemCraftingDamaged("rubicCrystal");
	public static final Item pureRubicCrystal = new ItemCraftingDamaged("pureRubicCrystal").setMaxDamage(1000);
	public static final ItemPickaxeMultiSpeed rubyPickaxe = new ItemPickaxeMultiSpeed(Item.ToolMaterial.EMERALD, "rubyPickaxe");
	public static final ItemSpadeMultiSpeed rubySpade = new ItemSpadeMultiSpeed(Item.ToolMaterial.EMERALD, "rubySpade");
	
	public static void init() {
		GameRegistry.registerItem(ruby, "ruby");
		GameRegistry.registerItem(rubicCrystal, "rubicCrystal");
		GameRegistry.registerItem(pureRubicCrystal, "pureRubicCrystal");
		GameRegistry.registerItem(rubyPickaxe, "rubyPickaxe");
		GameRegistry.registerItem(rubySpade, "rubySpade");
	}
}
