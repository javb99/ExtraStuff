package com.javb.extrastuff.item.crafting;

import java.util.ArrayList;
import java.util.List;

import com.javb.extrastuff.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class RubicRecipe extends ShapelessRecipes {

	public RubicRecipe(ItemStack itemStack, List inputsList) {
		super(itemStack, inputsList);
		this.recipeItems.add(1, new ItemStack(ModItems.rubicCrystal));
	}
	
	public static List createList(Object ... objects)
    {
        ArrayList arraylist = new ArrayList();
        Object[] aobject = objects;
        int i = objects.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        return arraylist;
    }
}
