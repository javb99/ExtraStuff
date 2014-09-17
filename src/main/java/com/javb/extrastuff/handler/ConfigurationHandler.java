package com.javb.extrastuff.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;



public class ConfigurationHandler {
	
	public static Configuration configuration;
	public static boolean testValue;
	
	public static void init(File configFile) {
		if (configuration == null) {
			// Create the configuration object from the given configuration file.
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChange(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Reference.MODID)) {
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration() {
		boolean testValue = configuration.get(Configuration.CATEGORY_GENERAL, "test_config_file", true, "test boolean").getBoolean(true);
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
}
