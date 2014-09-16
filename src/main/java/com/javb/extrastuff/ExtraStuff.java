package com.javb.extrastuff;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExtraStuff.MODID, version = ExtraStuff.VERSION)
public class ExtraStuff
{
    public static final String MODID = "extrastuff";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLPreInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
