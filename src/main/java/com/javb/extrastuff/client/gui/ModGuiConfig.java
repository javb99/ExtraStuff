package com.javb.extrastuff.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.javb.extrastuff.handler.ConfigurationHandler;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig{

	public ModGuiConfig(GuiScreen parentScreen) {
		super(parentScreen, 
			  new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), 
			  Reference.MODID, 
			  false, 
			  false, 
			  GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
	}
	
}
