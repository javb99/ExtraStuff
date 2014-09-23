package com.javb.extrastuff.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.javb.extrastuff.init.ModItems;
import com.javb.extrastuff.reference.Reference;



public class CreativeTabES 
{
	public static final CreativeTabs ExtraStuff_tab = new CreativeTabs(Reference.MODID) 
	{
		
		public Item getTabIconItem()
		{
			return ModItems.ruby;
		}
		
		public String getTranslatedTabLable()
		{
			return "Extra Stuff";
		}
	};
}
