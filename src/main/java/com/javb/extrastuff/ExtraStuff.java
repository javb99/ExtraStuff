package com.javb.extrastuff;

import com.javb.extrastuff.handler.ConfigurationHandler;
import com.javb.extrastuff.init.ModBlock;
import com.javb.extrastuff.init.ModItems;
import com.javb.extrastuff.init.Recipes;
import com.javb.extrastuff.proxy.IProxy;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class ExtraStuff
{
    @Mod.Instance(Reference.MODID)
    public static ExtraStuff instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    	FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    	
    	ModItems.init();
    	ModBlock.init();
    	
    	
    	LogHelper.info("PreInit complete");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	LogHelper.info("Init complete");
    	Recipes.init();
    
    }
  
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	LogHelper.info("PostInit complete");
    }
}
