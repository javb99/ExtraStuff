package com.javb.extrastuff.init;

import net.minecraft.item.Item;

import com.javb.extrastuff.item.ESBaseItem;
import com.javb.extrastuff.item.ItemCraftingDamaged;
import com.javb.extrastuff.item.ItemNames;
import com.javb.extrastuff.item.ItemPickaxeMultiSpeed;
import com.javb.extrastuff.item.ItemSpadeMultiSpeed;
import com.javb.extrastuff.item.ItemSwordMultiDamage;
import com.javb.extrastuff.item.powered.ESBaseEnergyItem;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	
	public static final ESBaseItem ruby = new ESBaseItem(ItemNames.RUBY_NAME);
	public static final Item rubicCrystal = new ItemCraftingDamaged(ItemNames.RUBIC_CRYSTAL_NAME);
	public static final Item pureRubicCrystal = new ItemCraftingDamaged(ItemNames.PURE_RUBIC_CRYSTAL_NAME).setMaxDamage(1000);
	public static final ItemPickaxeMultiSpeed rubyPickaxe = new ItemPickaxeMultiSpeed(Item.ToolMaterial.EMERALD, ItemNames.RUBY_PICKAXE_NAME);
	public static final ItemSpadeMultiSpeed rubySpade = new ItemSpadeMultiSpeed(Item.ToolMaterial.EMERALD, ItemNames.RUBY_SHOVEL_NAME);
	public static final ItemSwordMultiDamage rubySword = new ItemSwordMultiDamage(Item.ToolMaterial.EMERALD, ItemNames.RUBY_SWORD_NAME);
	
	public static final Item rubyPowered = new ESBaseEnergyItem(100000, 1000, 1000).setUnlocalizedName(ItemNames.RUBY_POWERED);
	
	public static void init() {
		GameRegistry.registerItem(ruby, ItemNames.RUBY_NAME);
		GameRegistry.registerItem(rubyPowered, ItemNames.RUBY_POWERED);
		GameRegistry.registerItem(rubicCrystal, ItemNames.RUBIC_CRYSTAL_NAME);
		GameRegistry.registerItem(pureRubicCrystal, ItemNames.PURE_RUBIC_CRYSTAL_NAME);
		GameRegistry.registerItem(rubyPickaxe, ItemNames.RUBY_PICKAXE_NAME);
		GameRegistry.registerItem(rubySpade, ItemNames.RUBY_SHOVEL_NAME);
		GameRegistry.registerItem(rubySword, ItemNames.RUBY_SWORD_NAME);
	}
}
