package com.javb.extrastuff;

import com.javb.extrastuff.configure.ConfigurationHandler;
import com.javb.extrastuff.proxy.IProxy;
import com.javb.extrastuff.reference.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class ExtraStuff
{
    @Mod.Instance(Reference.MODID)
    public static ExtraStuff instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
