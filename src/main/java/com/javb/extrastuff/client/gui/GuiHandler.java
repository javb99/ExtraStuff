package com.javb.extrastuff.client.gui;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.javb.extrastuff.inventory.ContainerRubyCrafting;
import com.javb.extrastuff.inventory.ContainerRubyUpgrader;
import com.javb.extrastuff.tileentity.TileEntityRubyUpgrader;
import com.javb.extrastuff.utility.LogHelper;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    //returns an instance of the Container you made earlier
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    	LogHelper.info("getServerGuiElement");
    	switch (id) {
    	case 0:
    		TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityRubyUpgrader){
                    return new ContainerRubyUpgrader(player.inventory, (TileEntityRubyUpgrader) tileEntity);
            }
            break;
    	case 1:
    		return new ContainerRubyCrafting(player.inventory, world, x, y, z);
    		//break;
    	}
        
        return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    	LogHelper.info("getClientGuiElement");
    	switch (id) {
    	case 0:
    		TileEntity tileEntity = world.getTileEntity(x, y, z);
    	    if(tileEntity instanceof TileEntityRubyUpgrader){
    	            return new GuiRubyUpgrader(player.inventory, (TileEntityRubyUpgrader) tileEntity);
    	    }
    		break;
    	case 1:
    		return new GuiRubyCrafting(player.inventory, world, x, y, z);
    		//break;
    	}
	    
	    return null;
    }
}
